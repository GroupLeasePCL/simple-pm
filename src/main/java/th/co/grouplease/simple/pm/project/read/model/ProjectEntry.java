package th.co.grouplease.simple.pm.project.read.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.common.BaseEntity;
import th.co.grouplease.simple.pm.product.domain.model.Product;
import th.co.grouplease.simple.pm.project.domain.model.ProjectStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ProjectEntry")
@Table(name = "project_entry")
@SQLDelete(sql =
        "UPDATE project_entry " +
                "SET deleted = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findProjectEntryById")
@NamedQuery(name = "findProjectEntryById", query =
        "SELECT p " +
                "FROM ProjectEntry p " +
                "WHERE " +
                "    p.id = ?1 AND " +
                "    p.deleted = false")
@Where(clause = "deleted = false")
public class ProjectEntry extends BaseEntity {
    @NotNull(message = "productId cannot be null")
    private String projectId;
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
}
