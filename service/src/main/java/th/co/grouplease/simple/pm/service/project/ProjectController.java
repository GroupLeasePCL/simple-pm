package th.co.grouplease.simple.pm.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import th.co.grouplease.simple.pm.service.common.OffsetBasedPageRequest;
import th.co.grouplease.simple.pm.service.project.command.CreateProjectCommand;
import th.co.grouplease.simple.pm.service.project.command.DeleteProjectCommand;
import th.co.grouplease.simple.pm.service.project.command.StartProjectCommand;
import th.co.grouplease.simple.pm.service.project.read.model.ProjectEntry;
import th.co.grouplease.simple.pm.service.project.repository.ProjectEntryRepository;
import th.co.grouplease.simple.pm.service.project.service.ProjectService;
import th.co.grouplease.simple.pm.service.workinglog.ProjectWorkingEntryDto;
import th.co.grouplease.simple.pm.service.workinglog.repository.WorkingEntryRepository;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectEntryRepository projectEntryRepository;
    @Autowired
    private WorkingEntryRepository workingEntryRepository;

    @PostMapping
    public void create(@RequestBody @Valid CreateProjectCommand command){
        projectService.createProject(command);
    }

    @PutMapping(path = "/{projectId}/", produces = "application/project-start+json")
    public void start(@PathVariable String projectId, @RequestBody @Valid StartProjectCommand command){
        projectService.startProject(command);
    }

    @DeleteMapping("/{projectId}")
    public void delete(@PathVariable String projectId){
        projectService.deleteProject(new DeleteProjectCommand(projectId));
    }

    @GetMapping
    public List<ProjectEntry> getAllProjects(@RequestParam @Min(value = 0, message = "offset be at least 0") long offset,
                                             @RequestParam @Min(value = 1, message = "limit must be at least 1") int limit){
        //return projectEntryRepository.findAll(PageRequest.of(page, pageSize));
        return projectEntryRepository.findAll(new OffsetBasedPageRequest(offset, limit)).getContent();
    }

    @GetMapping(produces = "application/product-name-only+json")
    public Page<ProjectNameDto> getAllProjectNames(@RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                                   @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return projectEntryRepository.findAllProjectedBy(ProjectNameDto.class, PageRequest.of(page, pageSize));
    }

    @GetMapping(path = "/count")
    public Long getCount(){
        return projectEntryRepository.count();
    }

    @GetMapping("/{projectId}/working-entries")
    public Page<ProjectWorkingEntryDto> getWorkingEntriesByProject(@PathVariable String projectId,
                                                                   @RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                                                   @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return workingEntryRepository.findAllByProjectId(projectId,
                ProjectWorkingEntryDto.class,
                PageRequest.of(page, pageSize)
        );
    }

    @GetMapping("/{projectId}/working-entries/count")
    public Long getWorkingEntryCountForProject(@PathVariable String projectId){
        return workingEntryRepository.countAllByProjectId(projectId);
    }
}
