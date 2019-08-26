package th.co.grouplease.simple.pm.service.resource.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.service.resource.domain.model.Resource;
import th.co.grouplease.simple.pm.service.resource.domain.model.ResourceTeam;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Page<Resource> findAllByResourceTeam(ResourceTeam resourceTeam, Pageable pageable);
    Long countAllByResourceTeam(ResourceTeam resourceTeam);
}
