package th.co.grouplease.simple.pm.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping(produces = "application/product+json")
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    @GetMapping(produces = "application/product-name-only+json")
    public List<ProjectNameDto> getAllProjectNames(){
        return projectRepository.findAllProjectedBy(ProjectNameDto.class);
    }
}
