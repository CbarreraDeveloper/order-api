package com.orderapi.order_api.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
}
