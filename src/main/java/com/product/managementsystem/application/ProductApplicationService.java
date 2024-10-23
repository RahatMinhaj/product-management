package com.product.managementsystem.application;

import com.product.managementsystem.component.exception.ProductNotFoundException;
import com.product.managementsystem.domain.Product;
import com.product.managementsystem.dto.ProductDto;
import com.product.managementsystem.mapper.ProductMapper;
import com.product.managementsystem.param.ProductParam;
import com.product.managementsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductApplicationService {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductApplicationService(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    public Page<ProductDto> findAll(Pageable pageable) {
        return productService.getAll(pageable).map(this::entityToDto);
    }

    public List<ProductDto> findAll(Sort sort) {
        return productService.getAll(sort).stream().map(this::entityToDto).toList();
    }

    public List<ProductDto> findAll() {
        return productService.getAll().stream().map(this::entityToDto).toList();
    }

    public ProductDto findById(Long id) {
        return productService.findById(id).map(this::entityToDto).orElse(null);
    }

    public ProductDto createProduct(ProductParam param) {
        Product product = Product.create(param);
        return entityToDto(productService.createProduct(product));
    }

    public ProductDto updateProduct(Long id, ProductParam param) {
        Product product = productService.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
        product.update(param);
        return productService.updateProduct(product).map(this::entityToDto).orElse(null);
    }

    public void deleteProduct(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
        productService.deleteProduct(id);
    }

    public ProductDto updateStock(Long id, Integer stockQuantity) {
        Product product = productService.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
        product.updateStock(stockQuantity);
        return productService.updateStock(product).map(this::entityToDto).orElse(null);
    }

    private ProductDto entityToDto(Product entity) {
        ProductDto productDto = productMapper.entityToDto(entity);
        return productDto;
    }
}
