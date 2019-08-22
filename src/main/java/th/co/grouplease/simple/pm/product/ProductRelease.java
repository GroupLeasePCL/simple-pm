package th.co.grouplease.simple.pm.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import th.co.grouplease.simple.pm.AuditableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class ProductRelease extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
