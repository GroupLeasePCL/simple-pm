package th.co.grouplease.simple.pm.workinglog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import th.co.grouplease.simple.pm.AuditableEntity;
import th.co.grouplease.simple.pm.product.ProductRelease;
import th.co.grouplease.simple.pm.project.Project;
import th.co.grouplease.simple.pm.resource.Resource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class WorkingEntry extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProductRelease getRelease() {
        return release;
    }

    public void setRelease(ProductRelease release) {
        this.release = release;
    }

    public LocalDate getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(LocalDate workingDate) {
        this.workingDate = workingDate;
    }
}
