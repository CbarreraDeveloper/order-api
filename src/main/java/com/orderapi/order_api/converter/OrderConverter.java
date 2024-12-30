package com.orderapi.order_api.converter;

import com.orderapi.order_api.dtos.OrderDTO;
import com.orderapi.order_api.dtos.OrderLineDTO;
import com.orderapi.order_api.entity.Order;

import java.util.List;

public class OrderConverter extends AbstractConverter<Order, OrderDTO>{

    @Override
    public OrderDTO fromEntity(Order entity) {

        if(entity == null) return null;

     //   List<OrderLineDTO> lines = FromEntity(entity.getLines()); continuar desde el minuto 07:15

        return OrderDTO.builder()
                .id(entity.getId())
               // .lines(lines)
                .regDate(null)
                .total(entity.getTotal())
                .build();
    }

    @Override
    public Order fromDTO(OrderDTO dto) {
        return null;
    }

}
