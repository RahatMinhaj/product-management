package com.product.managementsystem.event;

import com.product.managementsystem.domain.Product;

public record ProductCreatedEvent(Product product) {
}
