package th.co.grouplease.simple.pm.product.event;

import lombok.Value;

@Value
public class ProductReleaseDeletedEvent {
    private String id;
}
