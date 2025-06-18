package com.adesso.order_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.adesso.order_service.domain.Order;
import com.adesso.order_service.domain.OrderEvent;
import com.adesso.order_service.domain.OrderItem;
import com.adesso.order_service.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

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
    public Order saveOrder(Order order) {
    Order savedOrder = orderRepository.save(order);

    for (OrderItem item : savedOrder.getItems()) {
        placeOrder(new OrderEvent(item.getId().toString()));
    }

    return savedOrder;
}
    

    @Override
    public boolean isExists(Long id) {
        return orderRepository.existsById(id);
    }

    public void placeOrder(OrderEvent event) {
        kafkaTemplate.send("order.created", event.getProductId(), event);
    }
}
