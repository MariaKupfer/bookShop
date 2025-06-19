package com.adesso.shopping_cart_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adesso.shopping_cart_service.domain.ShoppingCartEntity;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long>{

}
