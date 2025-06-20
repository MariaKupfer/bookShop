package com.adesso.checkout_service.services;

import java.util.Optional;

import com.adesso.checkout_service.domain.CheckoutEntity;

public interface CheckoutService {

    CheckoutEntity saveCheckout(CheckoutEntity checkout);
    
    Optional<CheckoutEntity> getCheckoutById(Long id);

    void deleteCheckout(Long id);

    boolean isExists(Long id);

    void prosessOrder(Long id);
    
}
