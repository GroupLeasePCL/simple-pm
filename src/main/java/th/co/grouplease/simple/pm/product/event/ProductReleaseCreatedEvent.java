package th.co.grouplease.simple.pm.product.event;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ProductReleaseCreatedEvent {
    private String id;
    private String productId;
    private String version;
    private LocalDate releaseDate;
}
