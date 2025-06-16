package com.adesso.shop.service;

import java.util.List;

import com.adesso.shop.domain.Book;

public interface BookService {

    List<Book> getAllBooks();

    Book saveBook(Book book);
    
    Book getBookById(Long id);

    void deleteBook(Long id);

    Book updateBook(Book newBook, Long id);

    List<Book> getBookByTitle(String title);
    
}
