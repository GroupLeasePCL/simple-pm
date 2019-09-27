package th.co.grouplease.simple.pm.service.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.service.project.command.CreateProjectCommand;
import th.co.grouplease.simple.pm.service.project.command.DeleteProjectCommand;
import th.co.grouplease.simple.pm.service.project.command.StartProjectCommand;
import th.co.grouplease.simple.pm.service.project.domain.model.Project;
import th.co.grouplease.simple.pm.service.project.repository.ProjectRepository;

import javax.transaction.Transactional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public Project createProject(CreateProjectCommand command){
        return projectRepository.save(new Project(command));
    }

    @Transactional
    public void deleteProject(DeleteProjectCommand command){
        var project = projectRepository.findById(command.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        project.markDeleted(command);
        projectRepository.save(project);
    }

    @Transactional
    public void startProject(StartProjectCommand command){
        var project = projectRepository.findById(command.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        project.start(command);

        projectRepository.save(project);
    }
}
