package th.co.grouplease.simple.pm.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReleaseRepository extends JpaRepository<ProductRelease, Long> {
    Page<ProductRelease> findAllByProduct(Product product, Pageable pageable);
    Long countAllByProduct(Product product);
}
