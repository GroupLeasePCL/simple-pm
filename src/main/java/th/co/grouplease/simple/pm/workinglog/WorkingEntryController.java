package th.co.grouplease.simple.pm.workinglog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/working-entries")
public class WorkingEntryController {

    @Autowired
    private WorkingEntryRepository workingEntryRepository;

    @GetMapping(produces = "application/working-entry+json")
    public List<WorkingEntry> getAllWorkingEntries(){
        return workingEntryRepository.findAll();
    }

    @GetMapping(produces = "application/project-working-entry+json")
    public List<ProjectWorkingEntryDto> getAllProjectWorkingEntries(){
        return workingEntryRepository.findAllProjectedBy(ProjectWorkingEntryDto.class);
    }
}
