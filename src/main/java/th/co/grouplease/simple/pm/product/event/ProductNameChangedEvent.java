package th.co.grouplease.simple.pm.product.event;

import lombok.Value;

@Value
public class ProductNameChangedEvent {
    private String id;
    private String name;
}
