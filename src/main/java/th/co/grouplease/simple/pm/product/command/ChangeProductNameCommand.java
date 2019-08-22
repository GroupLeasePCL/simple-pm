package th.co.grouplease.simple.pm.product.command;

import javax.validation.constraints.NotNull;

public class ChangeProductNameCommand {
    @NotNull(message = "Name cannot be null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
