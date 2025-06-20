package com.adesso.shopping_cart_service.services;

import java.util.Optional;

import com.adesso.shopping_cart_service.domain.ShoppingCartEntity;
import com.adesso.shopping_cart_service.domain.dto.ShoppingCartDTO;

public interface ShoppingCartService {

    ShoppingCartEntity createShoppingCartEntity(ShoppingCartEntity shoppingCartEntity);

    Optional<ShoppingCartEntity> getShoppingCartEntityById(Long id);

    void deleteShoppingCartEntity(Long id);

    boolean isExists(Long id);

    void checkout(ShoppingCartDTO id);

}
