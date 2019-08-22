package th.co.grouplease.simple.pm.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.product.Product;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findAllByProduct(Product product, Pageable pageable);
    <T> Page<T> findAllProjectedBy(Class<T> type, Pageable pageable);
    Long countAllByProduct(Product product);
}
