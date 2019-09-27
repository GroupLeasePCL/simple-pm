package th.co.grouplease.simple.pm.ui.model;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartProjectCommand {
    @NotNull
    private String id;

    public  StartProjectCommand(String id){
        this.id = id;
    }

}
