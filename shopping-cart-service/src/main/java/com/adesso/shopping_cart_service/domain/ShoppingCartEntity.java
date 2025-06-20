package com.adesso.shopping_cart_service.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_cart_seq")
    @SequenceGenerator(name = "shopping_cart_seq", sequenceName = "shopping_cart_seq", allocationSize = 50)
    @Column(name = "shopping_cart_id")
    private Long id;
    private Double amount;
    private String currency;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date();
    
    @OneToMany(mappedBy = "shoppingCartEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ShoppingCartItemEntity> items;

}
