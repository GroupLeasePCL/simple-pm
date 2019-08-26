package th.co.grouplease.simple.pm.service.workinglog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import th.co.grouplease.simple.pm.service.workinglog.domain.model.WorkingEntry;
import th.co.grouplease.simple.pm.service.workinglog.repository.WorkingEntryRepository;

import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(path = "/working-entries")
public class WorkingEntryController {

    @Autowired
    private WorkingEntryRepository workingEntryRepository;

    @GetMapping(produces = "application/working-entry+json")
    public Page<WorkingEntry> getAllWorkingEntries(@RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                                   @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return workingEntryRepository.findAll(PageRequest.of(page, pageSize));
    }

    @GetMapping(produces = "application/project-working-entry+json")
    public Page<ProjectWorkingEntryDto> getAllProjectWorkingEntries(@RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                                                    @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return workingEntryRepository.findAllProjectedBy(ProjectWorkingEntryDto.class, PageRequest.of(page, pageSize));
    }

    @GetMapping(path = "/count")
    public Long getCount(){
        return workingEntryRepository.count();
    }
}
