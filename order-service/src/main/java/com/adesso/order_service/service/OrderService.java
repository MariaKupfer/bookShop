package com.adesso.order_service.service;

import java.util.List;
import java.util.Optional;

import com.adesso.order_service.domain.Order;

public interface OrderService {

    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long id);

    void deleteOrder(Long id);

    Order saveOrder(Order order);

    boolean isExists(Long id);

}
