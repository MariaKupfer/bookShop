package com.adesso.order_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adesso.order_service.domain.Order;
import com.adesso.order_service.service.OrderService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private static final String PATH = "v1/api/orders";
    
    @GetMapping(PATH)
  public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

  @PostMapping(PATH)
  public ResponseEntity<Order> createOrder( @RequestBody Order newOrder) {
    return new ResponseEntity<Order>(orderService.saveOrder(newOrder), HttpStatus.CREATED);
  }

  @GetMapping(PATH + "/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
      Optional<Order> result = orderService.getOrderById(id);
      return result
        .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

/*   @PutMapping(PATH + "/{id}")
  public ResponseEntity<Order> updateOrder(@Valid @RequestBody OrderRequest order, @PathVariable Long id) {
    if (!orderService.isExists(id)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    order.setId(id);
    Order updatedOrder = orderService.saveOrder(order);
    return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
  } */

  @DeleteMapping(PATH + "/{id}")
  public ResponseEntity deleteOrder(@PathVariable Long id) {
    if (!orderService.isExists(id)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    orderService.deleteOrder(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
