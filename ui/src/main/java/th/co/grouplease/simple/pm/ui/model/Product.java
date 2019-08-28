package th.co.grouplease.simple.pm.ui.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel<Long> {
    @NotEmpty
    private String name;
    @NotNull
    private LocalDate productStartDate;
    private LocalDate productEndDate;
}
