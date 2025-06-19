package com.adesso.shopping_cart_service.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemDTO {
    private Long id;
    private Long bookId;
    private String title;
    private double price;
    private int quantity;
}
