package th.co.grouplease.simple.pm.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class StartProjectCommand {
    @NotNull
    private String id;
}
