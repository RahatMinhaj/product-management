package com.product.managementsystem.domain;

import com.product.managementsystem.component.exception.InvalidInputException;
import com.product.managementsystem.param.ProductParam;
import com.product.managementsystem.domain.core.BaseDomain;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = "products")
public class Product extends BaseDomain {
    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "category_name"))
    private Category category;


    public static Product create(ProductParam productParam) {
        validateFields(productParam);
        return Product.builder()
                .name(productParam.getName())
                .description(productParam.getDescription())
                .price(productParam.getPrice())
                .stockQuantity(productParam.getStockQuantity())
                .category(new Category(productParam.getCategoryName()))
                .build();
    }

    public void update(ProductParam param) {
        if (param.getName() != null) {
            this.name = param.getName();
        }
        if (param.getDescription() != null) {
            this.description = param.getDescription();
        }
        if (param.getPrice() != null) {
            this.price = param.getPrice();
        }
        if (param.getStockQuantity() != null) {
            this.stockQuantity = param.getStockQuantity();
        }
        if (param.getCategoryName() != null) {
            this.category = new Category(param.getCategoryName());
        }
    }

    private static void validateFields(ProductParam productParam) {
        if (productParam.getPrice() == null || productParam.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Price must be greater than zero.");
        }
        if (productParam.getStockQuantity() == null || productParam.getStockQuantity() < 0) {
            throw new InvalidInputException("Stock quantity cannot be negative.");
        }
        if (productParam.getName() == null || productParam.getName().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }
        if (StringUtils.isEmpty(productParam.getCategoryName())) {
            throw new InvalidInputException("Category cannot be empty.");
        }
    }

    public void updateStock(Integer stockQuantity) {
        if (stockQuantity == null || stockQuantity < 0) {
            throw new InvalidInputException("Stock quantity cannot be negative.");
        }
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Category getCategory() {
        return category;
    }
}
