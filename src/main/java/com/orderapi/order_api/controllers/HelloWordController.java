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

}
