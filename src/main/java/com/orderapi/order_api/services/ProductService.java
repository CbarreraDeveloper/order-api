package com.orderapi.order_api.services;

import com.orderapi.order_api.entity.Product;
import com.orderapi.order_api.exceptions.GeneralServiceException;
import com.orderapi.order_api.exceptions.NoDataFoundException;
import com.orderapi.order_api.exceptions.ValidateServiceException;
import com.orderapi.order_api.repository.ProductRepository;
import com.orderapi.order_api.validators.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public Product findById(Long productId){
        try {
            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new NoDataFoundException("No existe el producto"));
            return product;
        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
                log.error(e.getMessage(), e);
                throw new GeneralServiceException(e.getMessage());
        }
    }

    @Transactional
    public void delete(Long productId){

        try {
            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new NoDataFoundException("No existe el producto"));
            productRepo.delete(product);
        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage());
        }
    }

    public List<Product> findAll(Pageable page){
        try {
            List<Product> products = productRepo.findAll(page).toList();
            return products;
        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage());
        }
    }

    @Transactional
    public Product save(Product product){
        try {
            ProductValidator.save(product);

            if(product.getId() == null){
                Product newProduct = productRepo.save(product);
                return newProduct;
            }
            Product exitProduct = productRepo.findById(product.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe el producto"));
            exitProduct.setName(product.getName());
            exitProduct.setPrice(product.getPrice());

            productRepo.save(exitProduct);

            return exitProduct;
        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage());
        }
    }
}
