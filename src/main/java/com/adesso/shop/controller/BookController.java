package com.adesso.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adesso.shop.model.Book;
import com.adesso.shop.service.BookService;

@RestController
public class BookController {

    @Autowired
    private BookService bookService; 

BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/books")
    public List<Book> getAllProducts() {
        return bookService.getAllBooks();
    }

  @PostMapping("/books")
  Book newBook(@RequestBody Book newBook) {
    return bookService.saveBook(newBook);
  }

  // Single item
  
  @GetMapping("/books/{id}")
  Book one(@PathVariable Long id) {
    return bookService.getBookById(id);
  }

  @PutMapping("/books/{id}")
  Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
    return bookService.updateBook(newBook, id);
  }

  @DeleteMapping("/Books/{id}")
  void deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
  }
    
}
