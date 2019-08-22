package th.co.grouplease.simple.pm.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/teams")
public class ResourceTeamController {
    @Autowired
    private ResourceTeamRepository resourceTeamRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping
    public List<ResourceTeam> getAllTeams(){
        return resourceTeamRepository.findAll();
    }

    @GetMapping(path = "/{teamId}/resources")
    public List<Resource> getAllResourcesByTeam(@PathVariable Long teamId){
        var team = resourceTeamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return resourceRepository.findAllByResourceTeam(team);
    }
}
