package th.co.grouplease.simple.pm.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.co.grouplease.simple.pm.ui.model.Project;

import java.util.List;

@Service
public class ProjectService {
    private final RestTemplate restTemplate;

    public ProjectService(@Autowired RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Project> getProjects(long offset, int limit){
        var response = restTemplate.exchange(
                "http://localhost:8080/projects?offset={offset}&limit={limit}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Project>>(){},
                offset,
                limit);

        return response.getBody();
    }

    public int getProjectCount(){
        return Math.toIntExact(restTemplate.getForObject("http://localhost:8080/projects/count", Long.class));
    }

    public Project createProduct(Project projet) {
        return restTemplate.postForObject(
                "http://localhost:8080/projects",
                projet,
                Project.class
        );
    }


}
