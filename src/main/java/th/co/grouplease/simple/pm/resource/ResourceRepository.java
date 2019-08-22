package th.co.grouplease.simple.pm.resource;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findAllByResourceTeam(ResourceTeam resourceTeam);
}
