package com.adesso.shopping_cart_service.domain.dto;

import java.util.List;

import com.adesso.shopping_cart_service.domain.ShoppingCartItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
        private Long id;
    private List<ShoppingCartItemEntity> items;
}
