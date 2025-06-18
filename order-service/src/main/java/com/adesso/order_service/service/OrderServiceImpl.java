package com.adesso.order_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.adesso.order_service.domain.Order;
import com.adesso.order_service.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
         return StreamSupport.stream(orderRepository
                      .findAll()
                      .spliterator(), false)
                  .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order createOrder(Order order) {
       return orderRepository.save(order);
    }

    @Override
    public boolean isExists(Long id) {
        return orderRepository.existsById(id);
    }

}
