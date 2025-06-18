package com.adesso.shop.controller;

import java.util.List;
import java.util.Optional;

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
  public ResponseEntity<Book> addNewBook(@Valid @RequestBody Book newBook) {
    return new ResponseEntity<Book>(bookService.saveBook(newBook), HttpStatus.CREATED);
  }

  @GetMapping(PATH + "/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
      Optional<Book> result = bookService.getBookById(id);
      return result
        .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping(PATH + "/{id}")
  public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book, @PathVariable Long id) {
    if (!bookService.isExists(id)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    book.setId(id);
    Book updatedBook = bookService.saveBook(book);
    return new ResponseEntity<>(updatedBook, HttpStatus.OK);
  }

  @DeleteMapping(PATH + "/{id}")
  public ResponseEntity deleteBook(@PathVariable Long id) {
    if (!bookService.isExists(id)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    bookService.deleteBook(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // Exception Handling
  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Book not found with id: " + ex.getBookId());
  } 
}
