package th.co.grouplease.simple.pm.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class HoldProjectCommand {
    @NotNull
    private String id;
    @NotNull(message = "Hold date cannot be null")
    private LocalDate holdDate;
}
