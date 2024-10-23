package com.product.managementsystem.service;

import com.product.managementsystem.domain.Product;
import com.product.managementsystem.event.ProductCreatedEvent;
import com.product.managementsystem.infrastructure.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ProductService(ProductRepository productRepository, ApplicationEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
    }

    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> getAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new IllegalArgumentException("Product name must be unique.");
        }
        Product createdProduct = productRepository.save(product);
        eventPublisher.publishEvent(new ProductCreatedEvent(createdProduct));
        return createdProduct;
    }

    public Optional<Product> updateProduct(Product product) {
        return Optional.of(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> updateStock(Product product) {
        return Optional.of(productRepository.save(product));
    }
}
