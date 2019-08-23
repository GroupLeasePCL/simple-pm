package th.co.grouplease.simple.pm.product;

import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.AuditableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "Product")
@Table(name = "product")
@SQLDelete(sql =
        "UPDATE product " +
                "SET deleted = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findProductById")
@NamedQuery(name = "findProductById", query =
        "SELECT p " +
                "FROM Product p " +
                "WHERE " +
                "    p.id = ?1 AND " +
                "    p.deleted = false")
@Where(clause = "deleted = false")
public class Product extends AuditableEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Product start date cannot be null")
    private LocalDate productStartDate;

    private LocalDate productEndDate;

    public static Product create(String name, LocalDate productStartDate){
        var product = new Product();
        product.setName(name);
        product.setProductStartDate(productStartDate);
        return product;
    }

    public Product withEndDate(LocalDate productEndDate){
        this.setProductEndDate(productEndDate);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProductStartDate() {
        return productStartDate;
    }

    public void setProductStartDate(LocalDate productStartDate) {
        this.productStartDate = productStartDate;
    }

    public LocalDate getProductEndDate() {
        return productEndDate;
    }

    public void setProductEndDate(LocalDate productEndDate) {
        this.productEndDate = productEndDate;
    }
}
