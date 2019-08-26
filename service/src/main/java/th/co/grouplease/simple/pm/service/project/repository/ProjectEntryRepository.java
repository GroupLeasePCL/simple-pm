package th.co.grouplease.simple.pm.service.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.service.product.domain.model.Product;
import th.co.grouplease.simple.pm.service.project.read.model.ProjectEntry;

import java.util.Optional;

public interface ProjectEntryRepository extends JpaRepository<ProjectEntry, Long> {
    Page<ProjectEntry> findAllByProduct(Product product, Pageable pageable);
    Optional<ProjectEntry> findByProjectId(String projectId);
    <T> Page<T> findAllProjectedBy(Class<T> type, Pageable pageable);
    Long countAllByProduct(Product product);
}
