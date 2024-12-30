package com.orderapi.order_api.converter;

import com.orderapi.order_api.dtos.ProductDTO;
import com.orderapi.order_api.entity.Product;

public class ProductConverter extends AbstractConverter<Product, ProductDTO> {

    @Override
    public ProductDTO fromEntity(Product entity) {

        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }

    @Override
    public Product fromDTO(ProductDTO dto) {

        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
    }
}
