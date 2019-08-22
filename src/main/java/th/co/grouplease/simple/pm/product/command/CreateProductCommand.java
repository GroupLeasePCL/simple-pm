package th.co.grouplease.simple.pm.product.command;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateProductCommand {
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Product start date cannot be null")
    private LocalDate productStartDate;
    private LocalDate productEndDate;

    public static CreateProductCommand create(String name, LocalDate productStartDate, LocalDate productEndDate){
        var result = new CreateProductCommand();
        result.setName(name);
        result.setProductStartDate(productStartDate);
        return result;
    }

    public CreateProductCommand withEndDate(LocalDate productEndDate){
        this.setProductEndDate(productEndDate);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
