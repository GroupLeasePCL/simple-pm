package th.co.grouplease.simple.pm.project.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.common.BaseAggregateRootEntity;
import th.co.grouplease.simple.pm.project.command.CreateProjectCommand;
import th.co.grouplease.simple.pm.project.command.DeleteProjectCommand;
import th.co.grouplease.simple.pm.project.command.StartProjectCommand;
import th.co.grouplease.simple.pm.project.event.ProjectCreatedEvent;
import th.co.grouplease.simple.pm.project.event.ProjectDeletedEvent;
import th.co.grouplease.simple.pm.project.event.ProjectStartedEvent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Project")
@Table(name = "project")
@SQLDelete(sql =
        "UPDATE project " +
                "SET deleted = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findProjectById")
@NamedQuery(name = "findProjectById", query =
        "SELECT p " +
                "FROM Project p " +
                "WHERE " +
                "    p.id = ?1 AND " +
                "    p.deleted = false")
@Where(clause = "deleted = false")
public class Project extends BaseAggregateRootEntity<Project> {

    @NotNull(message = "Project status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private ProjectStatus status;

    public Project(CreateProjectCommand command){
        setId(command.getId());
        this.status = ProjectStatus.TODO;
        registerEvent(new ProjectCreatedEvent(command.getId(), command.getName(), command.getProductId(), this.status));
    }

    public void markDeleted(DeleteProjectCommand command){
        super.markDeleted();
        registerEvent(new ProjectDeletedEvent(command.getId()));
    }

    public void start(StartProjectCommand command){
        if(status != ProjectStatus.TODO){
            throw new IllegalStateException("Cannot start project because it's not in TODO state");
        } else {
            status = ProjectStatus.WIP;
            registerEvent(new ProjectStartedEvent(command.getId(), status));
        }
    }
}
