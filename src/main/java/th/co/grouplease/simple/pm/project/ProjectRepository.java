package th.co.grouplease.simple.pm.project;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.product.Product;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByProduct(Product product, Pageable pageable);
    <T> List<T> findAllProjectedBy(Class<T> type);
    Long countAllByProduct(Product product);
}
