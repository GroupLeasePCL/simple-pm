package th.co.grouplease.simple.pm.workinglog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.project.domain.model.Project;
import th.co.grouplease.simple.pm.resource.domain.model.Resource;
import th.co.grouplease.simple.pm.workinglog.domain.model.WorkingEntry;

public interface WorkingEntryRepository extends JpaRepository<WorkingEntry, Long> {
    <T> Page<T> findAllByProject(Project project, Class<T> type, Pageable pageable);
    Long countAllByProject(Project project);
    <T> Page<T> findAllByResource(Resource resource, Class<T> type, Pageable pageable);
    Long countAllByResource(Resource resource);
    <T> Page<T> findAllProjectedBy(Class<T> type, Pageable pageable);
}
