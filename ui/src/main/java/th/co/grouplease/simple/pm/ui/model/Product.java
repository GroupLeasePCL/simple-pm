package th.co.grouplease.simple.pm.ui.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class Product extends BaseModel<Long> {
    @NotEmpty
    private String name;
    @NotNull
    private LocalDate productStartDate;
    private LocalDate productEndDate;
}
