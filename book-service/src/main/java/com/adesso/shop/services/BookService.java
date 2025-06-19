package com.adesso.shop.services;

import java.util.List;
import java.util.Optional;

import com.adesso.shop.domain.BookEntity;

public interface BookService {

    List<BookEntity> getAllBooks();

    BookEntity saveBook(BookEntity book);
    
    Optional<BookEntity> getBookById(Long id);

    void deleteBook(Long id);

    List<BookEntity> getBookByTitle(String title);

    boolean isExists(Long id);
    
}
