package th.co.grouplease.simple.pm.product.command;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ChangeProductTimelineCommand {
    @NotNull(message = "Product start date cannot be null")
    private LocalDate productStartDate;
    private LocalDate productEndDate;

    public LocalDate getProductStartDate() {
        return productStartDate;
    }

    public void setProductStartDate(LocalDate productStartDate) {
        this.productStartDate = productStartDate;
    }

    public LocalDate getProductEndDate() {
        return productEndDate;
    }

    public void setProductEndDate(LocalDate productEndDate) {
        this.productEndDate = productEndDate;
    }
}
