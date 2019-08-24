package th.co.grouplease.simple.pm.product.event;

import lombok.Value;

@Value
public class ProductDeletedEvent {
    private String id;
}
