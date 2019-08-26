package th.co.grouplease.simple.pm.service.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CancelProjectCommand {
    @NotNull
    private String id;
    @NotNull(message = "Reason cannot be null")
    private String reason;
}
