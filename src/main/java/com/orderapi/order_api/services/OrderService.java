package com.orderapi.order_api.services;

import com.orderapi.order_api.entity.Order;
import com.orderapi.order_api.entity.OrderLine;
import com.orderapi.order_api.exceptions.GeneralServiceException;
import com.orderapi.order_api.exceptions.NoDataFoundException;
import com.orderapi.order_api.exceptions.ValidateServiceException;
import com.orderapi.order_api.repository.OrderLineRepository;
import com.orderapi.order_api.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderLineRepository orderLineRepo;

    public List<Order> findAll(Pageable page){
        try {
            return orderRepo.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public Order findByID(Long id){

        try {
            return orderRepo.findById(id).orElseThrow(() -> new NoDataFoundException("La Orden no Existe"));

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    public void delete(Long id) {

        try {
            Order order = orderRepo.findById(id).orElseThrow(() -> new NoDataFoundException("La Orden no Existe"));
            orderRepo.delete(order);

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Transactional
    public Order save(Order order) {
        try {
            order.getLines().forEach(line -> line.setOrder(order));

            if(order.getId() == null) {

                order.setRegDate(LocalDateTime.now());
                return orderRepo.save(order);
            }

            Order savedOrder = orderRepo.findById(order.getId())
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
            order.setRegDate(savedOrder.getRegDate());

            List<OrderLine> deletedLines = savedOrder.getLines();
            deletedLines.removeAll(order.getLines());
            orderLineRepo.deleteAll(deletedLines);

            return orderRepo.save(order);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
