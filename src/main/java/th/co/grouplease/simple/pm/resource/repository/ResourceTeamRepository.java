package th.co.grouplease.simple.pm.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.resource.domain.model.ResourceTeam;

public interface ResourceTeamRepository extends JpaRepository<ResourceTeam, Long> {
}
