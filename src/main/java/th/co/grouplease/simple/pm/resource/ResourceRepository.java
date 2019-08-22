package th.co.grouplease.simple.pm.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Page<Resource> findAllByResourceTeam(ResourceTeam resourceTeam, Pageable pageable);
    Long countAllByResourceTeam(ResourceTeam resourceTeam);
}
