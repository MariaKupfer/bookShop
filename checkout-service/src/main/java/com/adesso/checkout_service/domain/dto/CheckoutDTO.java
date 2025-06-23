package com.adesso.checkout_service.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.adesso.checkout_service.domain.Address;
import com.adesso.checkout_service.domain.ShoppingCartItemEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDTO {
    private Long id;

    private Long shoppingCartId;
    private Double amount;
    private String currency;
    private Address shippingAddress;
    private String paymentMethod;
    private List<ShoppingCartItemEntity> items;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private String email;
}
