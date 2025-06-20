package com.adesso.checkout_service.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adesso.checkout_service.domain.CheckoutEntity;
import com.adesso.checkout_service.repositories.CheckoutRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService{

    private final CheckoutRepository checkoutRepository;

    @Override
    public CheckoutEntity saveCheckout(CheckoutEntity checkout) {
        return checkoutRepository.save(checkout);
    }

    @Override
    public Optional<CheckoutEntity> getCheckoutById(Long id) {
       return checkoutRepository.findById(id);
    }

    @Override
    public void deleteCheckout(Long id) {
        checkoutRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
       return checkoutRepository.existsById(id);
    }

    @Override
    public void prosessOrder(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prosessOrder'");
    }

}
