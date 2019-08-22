package th.co.grouplease.simple.pm.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.workinglog.ProjectWorkingEntryDto;
import th.co.grouplease.simple.pm.workinglog.WorkingEntryRepository;

import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(path = "/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private WorkingEntryRepository workingEntryRepository;

    @GetMapping(produces = "application/product+json")
    public Page<Project> getAllProjects(@RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                        @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return projectRepository.findAll(PageRequest.of(page, pageSize));
    }

    @GetMapping(produces = "application/product-name-only+json")
    public Page<ProjectNameDto> getAllProjectNames(@RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                                   @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return projectRepository.findAllProjectedBy(ProjectNameDto.class, PageRequest.of(page, pageSize));
    }

    @GetMapping(path = "/count")
    public Long getCount(){
        return projectRepository.count();
    }

    @GetMapping("/{projectId}/working-entries")
    public Page<ProjectWorkingEntryDto> getWorkingEntriesByProject(@PathVariable Long projectId,
                                                                   @RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                                                   @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return workingEntryRepository.findAllByProject(
                projectRepository.findById(projectId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                ProjectWorkingEntryDto.class,
                PageRequest.of(page, pageSize)
        );
    }

    @GetMapping("/{projectId}/working-entries/count")
    public Long getWorkingEntryCountForProject(@PathVariable Long projectId){
        return workingEntryRepository.countAllByProject(
                projectRepository.findById(projectId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }
}
