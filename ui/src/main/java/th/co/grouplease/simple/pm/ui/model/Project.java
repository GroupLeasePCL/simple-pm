package th.co.grouplease.simple.pm.ui.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Project extends BaseModel<Long> {
    private String projectId;
    private String name;
    private LocalDate startDate;
    private LocalDate expectedStartDate;
    private LocalDate endDate;
    private LocalDate expectedEndDate;
    private String status;


}