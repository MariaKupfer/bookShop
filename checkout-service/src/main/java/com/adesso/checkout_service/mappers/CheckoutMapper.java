package com.adesso.checkout_service.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.adesso.checkout_service.domain.CheckoutEntity;
import com.adesso.checkout_service.domain.dto.CheckoutDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CheckoutMapper implements Mapper<CheckoutEntity, CheckoutDTO>{

    private ModelMapper modelMapper;

    @Override
    public CheckoutDTO mapTo(CheckoutEntity chekout) {
        return modelMapper.map(chekout, CheckoutDTO.class);
    }

    @Override
    public CheckoutEntity mapFrom(CheckoutDTO chekoutDto) {
        return modelMapper.map(chekoutDto, CheckoutEntity.class);
    }
    
}

