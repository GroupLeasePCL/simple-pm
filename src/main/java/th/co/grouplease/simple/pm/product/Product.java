package th.co.grouplease.simple.pm.product;

import th.co.grouplease.simple.pm.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Product extends AuditableEntity {

    @Id
    @GeneratedValue
    private Long id;

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
