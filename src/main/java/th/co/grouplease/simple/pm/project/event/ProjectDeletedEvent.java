package th.co.grouplease.simple.pm.project.event;

import lombok.Value;

@Value
public class ProjectDeletedEvent {
    private String id;
}
