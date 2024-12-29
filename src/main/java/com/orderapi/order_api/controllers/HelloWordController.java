package com.orderapi.order_api.controllers;

import com.orderapi.order_api.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloWordController {

    @GetMapping(value = "hello")
    public String hello() {
        return "Hello World";
    }

   @GetMapping(value = "products")
    public Product findProduct() {
       /* Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setCategorys("Category 1");*/

       log.info("findProduct =>");
        Product product = Product.builder()
                .id(1L)
                .name("Product 1")
                .categorys("categoria 1")
                .build();

        return product;

    }
}
