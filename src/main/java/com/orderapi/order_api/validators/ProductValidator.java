package com.orderapi.order_api.validators;

import com.orderapi.order_api.entity.Product;
import com.orderapi.order_api.exceptions.ValidateServiceException;

public class ProductValidator {

    public static void save(Product product) {

        if(product.getName() == null || product.getName().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }

        if(product.getName().length() > 100) {
            throw new ValidateServiceException("El nombre debe ser mayor que 100");

        }

        if(product.getPrice() == null) {
            throw new ValidateServiceException("El price es requerido");
        }

        if(product.getPrice() < 0) {
            throw new ValidateServiceException("El price debe ser mayor que 0");
        }
    }
}
