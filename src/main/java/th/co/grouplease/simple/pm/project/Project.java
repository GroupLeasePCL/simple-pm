package th.co.grouplease.simple.pm.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import th.co.grouplease.simple.pm.AuditableEntity;
import th.co.grouplease.simple.pm.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Project extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;

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

    public static Project create(String name){
        var project = new Project();
        project.setName(name);
        project.setStatus(ProjectStatus.TODO);
        return project;
    }

    public Project withProduct(Product product){
        this.setProduct(product);
        return this;
    }

    public Project withStatus(ProjectStatus status){
        this.setStatus(status);
        return this;
    }
}
