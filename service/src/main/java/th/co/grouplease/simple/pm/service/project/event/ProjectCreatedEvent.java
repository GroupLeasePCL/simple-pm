package th.co.grouplease.simple.pm.service.project.event;

import lombok.Value;
import th.co.grouplease.simple.pm.service.project.domain.model.ProjectStatus;

@Value
public class ProjectCreatedEvent {
    private String id;
    private String name;
    private Long productId;
    private ProjectStatus status;
}
