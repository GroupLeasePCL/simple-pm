package th.co.grouplease.simple.pm.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CreateProjectCommand {
    @NotNull(message = "Project name cannot be null")
    private String name;

}
