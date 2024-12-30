package com.orderapi.order_api.controllers;

import com.orderapi.order_api.converter.OrderConverter;
import com.orderapi.order_api.dtos.OrderDTO;
import com.orderapi.order_api.entity.Order;
import com.orderapi.order_api.utils.WrapperResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private OrderConverter converter = new OrderConverter();

    @GetMapping(value = "/orders")
    public ResponseEntity<WrapperResponse<List<OrderDTO>>> finAll(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ){
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Order> orders = null; //orderService.finAll

        return new WrapperResponse(true, "success", converter.fromEntity(orders))
                .createResponse();
    }

    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<WrapperResponse<OrderDTO>> findById(@PathVariable(name = "id") Long id){

        Order order = null; //orderService.findById(id);


        return new WrapperResponse(true, "succes", converter.fromEntity(order))
                .createResponse();
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> create(OrderDTO order){

        Order newOrder = null; //orderService.save(converter.fromDTO(order));

        return new WrapperResponse(true, "succes", converter.fromEntity(newOrder))
                .createResponse();

    }

    @PutMapping(value = "/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> update(OrderDTO order){

        Order newOrder = null; //orderService.save(converter.fromDTO(order));

        return new WrapperResponse(true, "succes", converter.fromEntity(newOrder))
                .createResponse();

    }

    @DeleteMapping(value = "/orders")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        //orderService.delete(id);
        return new WrapperResponse(true, "succes", null).createResponse();
    }
}
