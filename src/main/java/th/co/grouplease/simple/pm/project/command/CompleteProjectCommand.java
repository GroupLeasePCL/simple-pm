package th.co.grouplease.simple.pm.project.command;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class CompleteProjectCommand {
    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;
}