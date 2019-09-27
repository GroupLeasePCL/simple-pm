package th.co.grouplease.simple.pm.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.co.grouplease.simple.pm.ui.model.CreateProjectCommand;
import th.co.grouplease.simple.pm.ui.model.Project;
import th.co.grouplease.simple.pm.ui.model.StartProjectCommand;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    private final RestTemplate restTemplate;

    public ProjectService(@Autowired RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Project> getProjects(long offset, int limit){

        var header = new HttpHeaders();
        header.setContentType(new MediaType("application", "project+json"));
        var entity = new HttpEntity<>("body", header);

        var response = restTemplate.exchange(
                "http://localhost:8080/projects?offset={offset}&limit={limit}",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Project>>(){},
                offset,
                limit);

        return response.getBody();
    }

    public int getProjectCount(){
        return Math.toIntExact(restTemplate.getForObject("http://localhost:8080/projects/count", Long.class));
    }

    public Project createProject(Project project) {
        CreateProjectCommand newProject = new CreateProjectCommand(UUID.randomUUID().toString(),project.getName());
        if (project.getProduct() != null){
            newProject.setProductId(project.getProduct().getId());
        }
        return restTemplate.postForObject(
                "http://localhost:8080/projects",
                newProject,
                Project.class
        );
    }

    public Project startProject(Project project){
        StartProjectCommand startPm = new StartProjectCommand(project.getProjectId());
        var header = new HttpHeaders();
        header.setContentType(new MediaType("application", "project-start+json"));
        var entity = new HttpEntity<>(startPm, header);
         restTemplate.put("http://localhost:8080/projects/"+project.getProjectId(),entity);
        return project;
    }


}
