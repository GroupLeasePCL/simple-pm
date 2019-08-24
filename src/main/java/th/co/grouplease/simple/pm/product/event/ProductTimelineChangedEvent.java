package th.co.grouplease.simple.pm.product.event;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ProductTimelineChangedEvent {
    private String id;
    private LocalDate productStartDate;
    private LocalDate productEndDate;
}
