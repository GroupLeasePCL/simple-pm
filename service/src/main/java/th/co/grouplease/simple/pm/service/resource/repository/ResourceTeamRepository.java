package th.co.grouplease.simple.pm.service.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.service.resource.domain.model.ResourceTeam;

public interface ResourceTeamRepository extends JpaRepository<ResourceTeam, Long> {
}
