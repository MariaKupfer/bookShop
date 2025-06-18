package com.adesso.shop.service;

import java.util.List;
import java.util.Optional;

import com.adesso.shop.domain.Book;

public interface BookService {

    List<Book> getAllBooks();

    Book saveBook(Book book);
    
    Optional<Book> getBookById(Long id);

    void deleteBook(Long id);

    List<Book> getBookByTitle(String title);

    boolean isExists(Long id);
    
}
