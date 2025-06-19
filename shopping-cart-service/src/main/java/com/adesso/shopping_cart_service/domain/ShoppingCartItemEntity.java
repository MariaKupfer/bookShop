package com.adesso.shopping_cart_service.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_cart_item_seq")
    @SequenceGenerator( name = "shopping_cart_item_seq", sequenceName = "shopping_cart_item_seq", allocationSize = 50)
    private Long id;
    private Long bookId;
    private String title;
    private double price;
    private int quantity;
    @ManyToOne
    @JoinColumn(name="shopping_cart_id")
    @JsonBackReference
    private ShoppingCartEntity shoppingCartEntity;
}
