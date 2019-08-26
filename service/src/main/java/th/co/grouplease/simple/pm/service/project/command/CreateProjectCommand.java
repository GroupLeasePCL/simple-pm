package th.co.grouplease.simple.pm.service.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CreateProjectCommand {
    @NotNull
    private String id;
    @NotNull(message = "Project name cannot be null")
    private String name;
    private Long productId;
}
