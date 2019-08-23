package th.co.grouplease.simple.pm.project.command;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ChangeProjectTimelineCommand {
    private LocalDate startDate;
    private LocalDate expectedStartDate;
    private LocalDate endDate;
    private LocalDate expectedEndDate;
}
