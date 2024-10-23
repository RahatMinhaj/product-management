package com.product.managementsystem.eventlistener;

import com.product.managementsystem.event.ProductCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {
    @EventListener
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
        System.out.println("Product created: " + event.product().getName());
    }
}
