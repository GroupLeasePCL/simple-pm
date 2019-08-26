package th.co.grouplease.simple.pm.workinglog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.resource.domain.model.Resource;
import th.co.grouplease.simple.pm.workinglog.domain.model.WorkingEntry;

public interface WorkingEntryRepository extends JpaRepository<WorkingEntry, Long> {
    <T> Page<T> findAllByProjectId(String projectId, Class<T> type, Pageable pageable);
    Long countAllByProjectId(String projectId);
    <T> Page<T> findAllByResource(Resource resource, Class<T> type, Pageable pageable);
    Long countAllByResource(Resource resource);
    <T> Page<T> findAllProjectedBy(Class<T> type, Pageable pageable);
}
