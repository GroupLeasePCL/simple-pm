package th.co.grouplease.simple.pm.project.command;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class HoldProjectCommand {
    @NotNull(message = "Hold date cannot be null")
    private LocalDate holdDate;
}
