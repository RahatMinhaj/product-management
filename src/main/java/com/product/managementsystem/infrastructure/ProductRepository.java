package com.product.managementsystem.infrastructure;

import com.product.managementsystem.domain.Product;
import com.product.managementsystem.infrastructure.core.CustomRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CustomRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
