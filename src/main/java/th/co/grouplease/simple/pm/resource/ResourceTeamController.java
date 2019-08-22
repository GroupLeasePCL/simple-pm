package th.co.grouplease.simple.pm.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(path = "/teams")
public class ResourceTeamController {
    @Autowired
    private ResourceTeamRepository resourceTeamRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping
    public Page<ResourceTeam> getAllTeams(@RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                          @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return resourceTeamRepository.findAll(PageRequest.of(page, pageSize));
    }

    @GetMapping(path = "/count")
    public Long getCount(){
        return resourceTeamRepository.count();
    }

    @GetMapping(path = "/{teamId}/resources")
    public Page<Resource> getAllResourcesByTeam(@PathVariable Long teamId,
                                                @RequestParam @Min(value = 0, message = "Page must be at least 0") Integer page,
                                                @RequestParam @Min(value = 1, message = "Page size must be at least 1") Integer pageSize){
        return resourceRepository.findAllByResourceTeam(
                resourceTeamRepository.findById(teamId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                PageRequest.of(page, pageSize));
    }

    @GetMapping(path = "/{teamId}/resources/count")
    public Long getResourceCountByTeam(@PathVariable Long teamId){
        return resourceRepository.countAllByResourceTeam(
                resourceTeamRepository.findById(teamId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
