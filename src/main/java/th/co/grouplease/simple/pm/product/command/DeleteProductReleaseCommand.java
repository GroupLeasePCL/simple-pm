package th.co.grouplease.simple.pm.product.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class DeleteProductReleaseCommand {
    @NotNull
    private String id;
}
