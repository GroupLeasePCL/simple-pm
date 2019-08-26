package th.co.grouplease.simple.pm.service.project.event;

import lombok.Value;
import th.co.grouplease.simple.pm.service.project.domain.model.ProjectStatus;

@Value
public class ProjectStartedEvent {
    private String id;
    private ProjectStatus status;
}
