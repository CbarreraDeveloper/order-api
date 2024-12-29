package com.orderapi.order_api.controllers;

import com.orderapi.order_api.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private List<Product> products = new ArrayList<Product>();

    public ProductController(){
        for (int c = 0; c < 10; c++){
            products.add(Product.builder()
                    .id((c+1L))
                    .name("Product " + (c+1L))
                    .build());
        }
    }

    @GetMapping(value = "/products")
    public List<Product> findAll(){
        return this.products;
    }

    @GetMapping(value = "/products/{productId}")
    public Product findById(@PathVariable("productId")  Long productId){
        for(Product prod : this.products){
            if(prod.getId().longValue() == productId.longValue()){
                return prod;
            }
        }
        return null;
    }

   @PostMapping(value = "/products")
   public Product create(@RequestBody Product product){

        this.products.add(product);
        return product;
   }

    @PutMapping(value = "/products")
    public Product update(@RequestBody Product product){

        for(Product prod : this.products){
            if(prod.getId().longValue() == product.getId().longValue()){
                prod.setName(product.getName());
                return prod;
            }
        }
        throw new RuntimeException("No existe el producto");
    }

    @DeleteMapping(value = "products/{productId}")
    public void delete(@PathVariable("productId")  Long productId){
        Product deleteProdcut = null;
        for(Product prod : this.products){
            if(prod.getId().longValue() == productId.longValue()){
                deleteProdcut = prod;
                break;
            }
        }
        if(deleteProdcut == null){
            throw new RuntimeException("No existe el producto");
        }
        this.products.remove(deleteProdcut);
    }

}
