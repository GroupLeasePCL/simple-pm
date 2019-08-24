package th.co.grouplease.simple.pm.product.command;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class CreateProductReleaseCommand {
    @NotNull(message = "id cannot be null")
    private String id;
    @NotNull(message = "productId cannot be null")
    private String productId;
    @NotNull
    private String version;
    @NotNull
    private LocalDate releaseDate;
}
