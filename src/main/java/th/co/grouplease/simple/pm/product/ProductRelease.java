package th.co.grouplease.simple.pm.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "ProductRelease")
@Table(name = "product_release")
@SQLDelete(sql =
        "UPDATE product_release " +
                "SET deleted = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findProductReleaseById")
@NamedQuery(name = "findProductReleaseById", query =
        "SELECT p " +
                "FROM ProductRelease p " +
                "WHERE " +
                "    p.id = ?1 AND " +
                "    p.deleted = false")
@Where(clause = "deleted = false")
public class ProductRelease extends BaseEntity {
    @JsonIgnore
    @NotNull(message = "product cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull(message = "Version cannot be null")
    private String version;

    @NotNull(message = "Release date cannot be null")
    private LocalDate releaseDate;

    public static ProductRelease create(Product product, String version, LocalDate releaseDate){
        var release = new ProductRelease();
        release.setProduct(product);
        release.setVersion(version);
        release.setReleaseDate(releaseDate);
        return release;
    }
}
