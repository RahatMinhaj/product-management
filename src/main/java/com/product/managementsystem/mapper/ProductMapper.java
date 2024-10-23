package com.product.managementsystem.mapper;

import com.product.managementsystem.config.ConfigMapper;
import com.product.managementsystem.domain.Product;
import com.product.managementsystem.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface ProductMapper {
    ProductDto entityToDto(Product entity);
}