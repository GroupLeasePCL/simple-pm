package th.co.grouplease.simple.pm.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.common.BaseAggregateRootEntity;
import th.co.grouplease.simple.pm.product.domain.model.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
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

    public static Project create(String id, String name){
        var project = new Project();
        project.setId(id);
        project.setName(name);
        project.setStatus(ProjectStatus.TODO);
        return project;
    }

    public Project withProduct(Product product){
        this.setProduct(product);
        return this;
    }
}
