package com.orderapi.order_api.repository;

import com.orderapi.order_api.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.orderapi.order_api.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

