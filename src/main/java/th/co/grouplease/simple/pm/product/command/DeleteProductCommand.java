package th.co.grouplease.simple.pm.product.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class DeleteProductCommand {
    @NotNull(message = "id cannot be null")
    private String id;
}
