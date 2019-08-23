package th.co.grouplease.simple.pm.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class ChangeProjectNameCommand {
    @NotNull(message = "Name cannot be null")
    private String name;
}
