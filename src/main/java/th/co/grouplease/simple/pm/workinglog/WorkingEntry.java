package th.co.grouplease.simple.pm.workinglog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.BaseEntity;
import th.co.grouplease.simple.pm.product.ProductRelease;
import th.co.grouplease.simple.pm.project.Project;
import th.co.grouplease.simple.pm.resource.Resource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "WorkingEntry")
@Table(name = "working_entry")
@SQLDelete(sql =
        "UPDATE working_entry " +
                "SET deleted = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findWorkingEntryById")
@NamedQuery(name = "findWorkingEntryById", query =
        "SELECT w " +
                "FROM WorkingEntry w " +
                "WHERE " +
                "    w.id = ?1 AND " +
                "    w.deleted = false")
@Where(clause = "deleted = false")
public class WorkingEntry extends BaseEntity {
    @JsonIgnore
    @NotNull(message = "Resource cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @NotNull(message = "Type of work cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private TypeOfWork typeOfWork;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "software_release_id")
    private ProductRelease release;

    @NotNull(message = "Working date cannot be null")
    private LocalDate workingDate;

    public static WorkingEntry create(Resource resource,
                                      TypeOfWork typeOfWork,
                                      LocalDate workingDate){
        var result = new WorkingEntry();
        result.setResource(resource);
        result.setTypeOfWork(typeOfWork);
        result.setWorkingDate(workingDate);
        return result;
    }

    public WorkingEntry withProject(Project project){
        this.setProject(project);
        return this;
    }

    public WorkingEntry withRelease(ProductRelease productRelease){
        this.setRelease(productRelease);
        return this;
    }
}
