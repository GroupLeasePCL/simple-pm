package th.co.grouplease.simple.pm.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import th.co.grouplease.simple.pm.workinglog.WorkingEntry;
import th.co.grouplease.simple.pm.workinglog.WorkingEntryRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/resources")
public class ResourceController {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private WorkingEntryRepository workingEntryRepository;

    @GetMapping
    public List<Resource> getAllResources(){
        return resourceRepository.findAll();
    }

    @GetMapping("/{resourceId}/working-entries")
    public List<WorkingEntry> getAllWorkingEntriesByResource(@PathVariable Long resourceId){
        var resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return workingEntryRepository.findAllByResource(resource);
    }
}
