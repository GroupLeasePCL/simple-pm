package th.co.grouplease.simple.pm.project.event;

import lombok.Value;
import th.co.grouplease.simple.pm.project.domain.model.ProjectStatus;

@Value
public class ProjectStartedEvent {
    private String id;
    private ProjectStatus status;
}
