package th.co.grouplease.simple.pm.product.event;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ProductCreatedEvent {
    private String id;
    private String name;
    private LocalDate productStartDate;
    private LocalDate productEndDate;
}
