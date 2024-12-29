package com.orderapi.order_api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class Product {

    private Long id;
    private String name;
}
