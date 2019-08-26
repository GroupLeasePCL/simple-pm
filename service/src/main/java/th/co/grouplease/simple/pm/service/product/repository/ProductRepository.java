package th.co.grouplease.simple.pm.service.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.service.product.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
