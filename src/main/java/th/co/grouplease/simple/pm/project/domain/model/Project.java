package th.co.grouplease.simple.pm.project.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.common.BaseAggregateRootEntity;
import th.co.grouplease.simple.pm.product.domain.model.Product;
import th.co.grouplease.simple.pm.project.command.CreateProjectCommand;
import th.co.grouplease.simple.pm.project.command.DeleteProjectCommand;
import th.co.grouplease.simple.pm.project.event.ProjectCreatedEvent;
import th.co.grouplease.simple.pm.project.event.ProjectDeletedEvent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    @NotNull(message = "Project name cannot be null")
    private String name;
    private LocalDate startDate;
    private LocalDate expectedStartDate;
    private LocalDate endDate;
    private LocalDate expectedEndDate;

    @NotNull(message = "Project status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private ProjectStatus status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Project(CreateProjectCommand command, Product product){
        setId(command.getId());
        this.name = command.getName();
        this.status = ProjectStatus.TODO;
        this.product = product;
        registerEvent(new ProjectCreatedEvent(command.getId(), command.getName(), command.getProductId(), this.status));
    }

    public void markDeleted(DeleteProjectCommand command){
        super.markDeleted();
        registerEvent(new ProjectDeletedEvent(command.getId()));
    }
}
