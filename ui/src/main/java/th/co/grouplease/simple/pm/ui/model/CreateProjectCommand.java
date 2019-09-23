package th.co.grouplease.simple.pm.ui.model;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateProjectCommand {
    @NotNull
    private String id;
    @NotNull(message = "Project name cannot be null")
    private String name;
    private Long productId;
    public  CreateProjectCommand(String id,String name){
        this.id = id;
        this.name = name;
    }
}
