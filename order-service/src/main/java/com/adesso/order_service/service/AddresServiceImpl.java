package com.adesso.order_service.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adesso.order_service.domain.Address;
import com.adesso.order_service.repository.AddressRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddresServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Override
    public Optional<Address> getAddresById(Long id) {
         return addressRepository.findById(id);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public boolean isExists(Long id) {
        return addressRepository.existsById(id);
    }
    
}
