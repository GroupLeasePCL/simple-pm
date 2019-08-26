package th.co.grouplease.simple.pm.service.project.command;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StartProjectCommand {
    @NotNull
    private String id;
}
