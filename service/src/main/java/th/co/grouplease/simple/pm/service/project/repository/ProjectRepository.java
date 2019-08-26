package th.co.grouplease.simple.pm.service.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.service.project.domain.model.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
