package th.co.grouplease.simple.pm.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.workinglog.WorkingEntry;
import th.co.grouplease.simple.pm.workinglog.WorkingEntryRepository;

import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(path = "/resources")
public class ResourceController {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private WorkingEntryRepository workingEntryRepository;

    @GetMapping
    public Page<Resource> getAllResources(@RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                          @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return resourceRepository.findAll(PageRequest.of(page, pageSize));
    }

    @GetMapping(path = "/count")
    public Long getCount(){
        return resourceRepository.count();
    }

    @GetMapping("/{resourceId}/working-entries")
    public Page<WorkingEntry> getAllWorkingEntriesByResource(@PathVariable String resourceId,
                                                             @RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                                             @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return workingEntryRepository.findAllByResource(
                resourceRepository.findById(resourceId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                WorkingEntry.class,
                PageRequest.of(page, pageSize));
    }

    @GetMapping("/{resourceId}/working-entries/count")
    public Long getWorkingEntryCountByResource(@PathVariable String resourceId){
        return workingEntryRepository.countAllByResource(resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
