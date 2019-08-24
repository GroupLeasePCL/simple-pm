package th.co.grouplease.simple.pm.product.command;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class CreateProductCommand {
    @NotNull(message = "id cannot be null")
    private String id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Product start date cannot be null")
    private LocalDate productStartDate;
    private LocalDate productEndDate;
}
