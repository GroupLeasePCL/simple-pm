package th.co.grouplease.simple.pm.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReleaseRepository extends JpaRepository<ProductRelease, Long> {
    List<ProductRelease> findAllByProduct(Product product);
}
