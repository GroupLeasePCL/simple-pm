package th.co.grouplease.simple.pm.service.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class DeleteProjectCommand {
    @NotNull
    private String id;
}
