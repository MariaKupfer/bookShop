package com.adesso.order_service.service;
import java.util.Optional;

import com.adesso.order_service.domain.Address;

public interface AddressService {

    Optional<Address> getAddresById(Long id);

    void deleteAddress(Long id);

    Address saveAddress(Address address);

    boolean isExists(Long id);
    
}
