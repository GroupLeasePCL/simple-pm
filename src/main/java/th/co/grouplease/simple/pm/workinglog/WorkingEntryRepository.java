package th.co.grouplease.simple.pm.workinglog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.project.Project;
import th.co.grouplease.simple.pm.resource.Resource;

import java.util.List;

public interface WorkingEntryRepository extends JpaRepository<WorkingEntry, Long> {
    <T> Page<T> findAllByProject(Project project, Class<T> type, Pageable pageable);
    Long countAllByProject(Project project);
    Page<WorkingEntry> findAllByResource(Resource resource, Pageable pageable);
    <T> List<T> findAllProjectedBy(Class<T> type);
}
