package com.adesso.shop.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.adesso.shop.domain.BookEntity;
import com.adesso.shop.domain.dto.BookDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BookMapper implements Mapper<BookEntity, BookDTO>{

    private ModelMapper modelMapper;

    @Override
    public BookDTO mapTo(BookEntity book) {
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookEntity mapFrom(BookDTO bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
    
}
