package th.co.grouplease.simple.pm.service.project.event;

import lombok.Value;

@Value
public class ProjectDeletedEvent {
    private String id;
}
