package com.adesso.shopping_cart_service.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.adesso.shopping_cart_service.domain.ShoppingCartEntity;
import com.adesso.shopping_cart_service.domain.dto.ShoppingCartDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShoppingCartMapper implements Mapper<ShoppingCartEntity, ShoppingCartDTO>{

    private ModelMapper modelMapper;

    @Override
    public ShoppingCartDTO mapTo(ShoppingCartEntity book) {
        return modelMapper.map(book, ShoppingCartDTO.class);
    }

    @Override
    public ShoppingCartEntity mapFrom(ShoppingCartDTO bookDto) {
        return modelMapper.map(bookDto, ShoppingCartEntity.class);
    }
    
}
