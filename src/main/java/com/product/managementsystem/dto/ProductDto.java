package com.product.managementsystem.dto;

import com.product.managementsystem.dto.core.BaseDto;
import com.product.managementsystem.domain.Category;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductDto extends BaseDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Category category;
}