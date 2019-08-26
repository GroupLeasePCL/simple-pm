package th.co.grouplease.simple.pm.service.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class ChangeProjectNameCommand {
    @NotNull
    private String id;
    @NotNull(message = "Name cannot be null")
    private String name;
}
