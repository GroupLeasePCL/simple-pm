package th.co.grouplease.simple.pm.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.product.repository.ProductRepository;
import th.co.grouplease.simple.pm.project.command.CreateProjectCommand;
import th.co.grouplease.simple.pm.project.command.DeleteProjectCommand;
import th.co.grouplease.simple.pm.project.domain.model.Project;
import th.co.grouplease.simple.pm.project.repository.ProjectRepository;

import javax.transaction.Transactional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Project createProject(CreateProjectCommand command){
        if(command.getProductId() != null){
            var product = productRepository.findById(command.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return projectRepository.save(new Project(command, product));
        } else {
            return projectRepository.save(new Project(command, null));
        }
    }

    @Transactional
    public void deleteProject(DeleteProjectCommand command){
        var project = projectRepository.findById(command.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        project.markDeleted(command);
        projectRepository.save(project);
    }
}
