package th.co.grouplease.simple.pm.product.command;

import javax.validation.constraints.NotNull;

public class DeleteProductCommand {
    @NotNull(message = "productId cannot be null")
    private Long productId;

    public DeleteProductCommand(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
