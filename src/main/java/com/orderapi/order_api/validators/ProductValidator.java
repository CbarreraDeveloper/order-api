package com.orderapi.order_api.validators;

import com.orderapi.order_api.entity.Product;

public class ProductValidator {

    public static void save(Product product) {

        if(product.getName() == null || product.getName().trim().isEmpty()) {
            throw new RuntimeException("El nombre es requerido");
        }

        if(product.getName().length() > 100) {
            throw new RuntimeException("El nombre debe ser mayor que 100");

        }

        if(product.getPrice() == null) {
            throw new RuntimeException("El price es requerido");
        }

        if(product.getPrice() < 0) {
            throw new RuntimeException("El price debe ser mayor que 0");
        }
    }
}
