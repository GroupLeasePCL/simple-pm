package th.co.grouplease.simple.pm.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.co.grouplease.simple.pm.project.domain.model.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
