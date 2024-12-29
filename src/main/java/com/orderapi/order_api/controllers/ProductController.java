package com.orderapi.order_api.controllers;

import com.orderapi.order_api.dtos.ProductDTO;
import com.orderapi.order_api.entity.Product;
import com.orderapi.order_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("productId")  Long productId){
        Product product = productService.findById(productId);

        ProductDTO productDTO = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "products/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId")  Long productId){
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<Product> products = productService.findAll();

        List<ProductDTO> dtoProducts =  products.stream().map(product -> {
            return ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();
        }).collect(Collectors.toList());

        return new ResponseEntity<List<ProductDTO>>(dtoProducts, HttpStatus.OK);
    }

   @PostMapping(value = "/products")
   public ResponseEntity<ProductDTO> create(@RequestBody Product product){
        Product newProduct = productService.save(product);

        ProductDTO productDTO = ProductDTO.builder()
                .id(newProduct.getId())
                .name(newProduct.getName())
                .price(newProduct.getPrice())
                .build();
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.CREATED);
   }

    @PutMapping(value = "/products")
    public ResponseEntity<ProductDTO> update(@RequestBody Product product){
        Product updateProduct = productService.save(product);

        ProductDTO productDTO = ProductDTO.builder()
                .id(updateProduct.getId())
                .name(updateProduct.getName())
                .price(updateProduct.getPrice())
                .build();

        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

}
