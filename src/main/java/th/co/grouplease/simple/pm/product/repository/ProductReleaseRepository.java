package th.co.grouplease.simple.pm.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.product.domain.model.Product;
import th.co.grouplease.simple.pm.product.domain.model.ProductRelease;

public interface ProductReleaseRepository extends JpaRepository<ProductRelease, Long> {
    Page<ProductRelease> findAllByProduct(Product product, Pageable pageable);
    Long countAllByProduct(Product product);
}
