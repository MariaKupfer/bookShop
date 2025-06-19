package com.adesso.shopping_cart_service.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adesso.shopping_cart_service.domain.ShoppingCartEntity;
import com.adesso.shopping_cart_service.repositories.ShoppingCartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService{

    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCartEntity createShoppingCartEntity(ShoppingCartEntity shoppingCartEntity) {
        return shoppingCartRepository.save(shoppingCartEntity);
    }

    @Override
    public Optional<ShoppingCartEntity> getShoppingCartEntityById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public void deleteShoppingCartEntity(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
       return shoppingCartRepository.existsById(id);
    }

}
