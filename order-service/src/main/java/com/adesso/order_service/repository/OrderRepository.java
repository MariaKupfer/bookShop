package com.adesso.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adesso.order_service.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
