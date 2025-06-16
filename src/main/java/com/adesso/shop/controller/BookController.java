package com.adesso.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adesso.shop.BookNotFoundException;
import com.adesso.shop.domain.Book;
import com.adesso.shop.service.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class BookController {

  private final BookService bookService; 
  private static final String PATH = "/books";

  @GetMapping(PATH)
  public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

  @PostMapping(PATH)
  public Book addNewBook(@Valid @RequestBody Book newBook) {
    return bookService.saveBook(newBook);
  }

  @GetMapping(PATH + "/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
      Book book = bookService.getBookById(id);
      return ResponseEntity.ok(book);
  }

  @PutMapping(PATH + "/{id}")
  public Book updateBook(@Valid @RequestBody Book newBook, @PathVariable Long id) {
    return bookService.updateBook(newBook, id);
  }

  @DeleteMapping(PATH + "/{id}")
  public void deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
  }

  // Exception Handling
  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Book not found with id: " + ex.getBookId());
  } 
}
