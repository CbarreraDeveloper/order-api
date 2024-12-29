package com.orderapi.order_api.controllers;

import com.orderapi.order_api.entity.Product;
import com.orderapi.order_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    private List<Product> products = new ArrayList<Product>();

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> findAll(){

        List<Product> products = productRepo.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<Product> findById(@PathVariable("productId")  Long productId){
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("No existe el producto"));
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

   @PostMapping(value = "/products")
   public ResponseEntity<Product> create(@RequestBody Product product){
        Product newProduct = productRepo.save(product);

        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
   }

    @PutMapping(value = "/products")
    public ResponseEntity<Product> update(@RequestBody Product product){

        Product exitProduct = productRepo.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("No existe el producto"));
        exitProduct.setName(product.getName());
        exitProduct.setPrice(product.getPrice());

        productRepo.save(exitProduct);

        return new ResponseEntity<Product>(exitProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "products/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId")  Long productId){
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("No existe el producto"));
        productRepo.delete(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
