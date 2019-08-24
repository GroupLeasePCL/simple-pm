package th.co.grouplease.simple.pm.product.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.common.BaseAggregateRootEntity;
import th.co.grouplease.simple.pm.product.command.ChangeProductNameCommand;
import th.co.grouplease.simple.pm.product.command.ChangeProductTimelineCommand;
import th.co.grouplease.simple.pm.product.command.CreateProductCommand;
import th.co.grouplease.simple.pm.product.command.DeleteProductCommand;
import th.co.grouplease.simple.pm.product.event.ProductCreatedEvent;
import th.co.grouplease.simple.pm.product.event.ProductDeletedEvent;
import th.co.grouplease.simple.pm.product.event.ProductNameChangedEvent;
import th.co.grouplease.simple.pm.product.event.ProductTimelineChangedEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
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
public class Product extends BaseAggregateRootEntity<Product> {

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Product start date cannot be null")
    private LocalDate productStartDate;

    private LocalDate productEndDate;

    public Product(CreateProductCommand command){
        setId(command.getId());
        this.name = command.getName();
        this.productStartDate = command.getProductStartDate();
        this.productEndDate = command.getProductEndDate();
        registerEvent(new ProductCreatedEvent(command.getId(), command.getName(), command.getProductStartDate(), command.getProductEndDate()));
    }

    public void delete(DeleteProductCommand command) {
        markDeleted();
        registerEvent(new ProductDeletedEvent(command.getId()));
    }

    public void changeName(ChangeProductNameCommand command){
        this.name = command.getName();
        registerEvent(new ProductNameChangedEvent(command.getId(), command.getName()));
    }

    public void changeTimeline(ChangeProductTimelineCommand command){
        this.productStartDate = command.getProductStartDate();
        this.productEndDate = command.getProductEndDate();
        registerEvent(new ProductTimelineChangedEvent(command.getId(), command.getProductStartDate(), command.getProductEndDate()));
    }
}
