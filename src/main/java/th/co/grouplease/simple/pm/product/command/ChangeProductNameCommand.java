package th.co.grouplease.simple.pm.product.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class ChangeProductNameCommand {
    @NotNull(message = "id cannot be null")
    private String id;
    @NotNull(message = "name cannot be null")
    private String name;
}
