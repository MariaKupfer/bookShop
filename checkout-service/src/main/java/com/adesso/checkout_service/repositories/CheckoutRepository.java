package com.adesso.checkout_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adesso.checkout_service.domain.CheckoutEntity;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Long>{
    
}
