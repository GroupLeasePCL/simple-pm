package th.co.grouplease.simple.pm.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CancelProjectCommand {
    @NotNull(message = "Reason cannot be null")
    private String reason;
}
