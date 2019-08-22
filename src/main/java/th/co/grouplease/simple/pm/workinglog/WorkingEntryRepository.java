package th.co.grouplease.simple.pm.workinglog;

import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.resource.Resource;

import java.util.List;

public interface WorkingEntryRepository extends JpaRepository<WorkingEntry, Long> {
    List<WorkingEntry> findAllByResource(Resource resource);
    <T> List<T> findAllProjectedBy(Class<T> type);
}
