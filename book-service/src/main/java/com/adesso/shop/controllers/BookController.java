package com.adesso.shop.controllers;

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
import com.adesso.shop.domain.BookEntity;
import com.adesso.shop.domain.dto.BookDTO;
import com.adesso.shop.mappers.Mapper;
import com.adesso.shop.services.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class BookController {

  private final BookService bookService;
  private Mapper<BookEntity, BookDTO> bookMapper;

  private static final String PATH = "v1/api/books";

  @GetMapping(PATH)
  public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }

  @PostMapping(PATH)
  public ResponseEntity<BookDTO> addNewBook(@Valid @RequestBody BookDTO newBook) {
    BookEntity book = bookMapper.mapFrom(newBook);
    BookEntity sevedBook = bookService.saveBook(book);
    return new ResponseEntity<BookDTO>(bookMapper.mapTo(sevedBook), HttpStatus.CREATED);
  }

  @GetMapping(PATH + "/{id}")
  public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
      Optional<BookEntity> result = bookService.getBookById(id);
      return result
        .map(book -> new ResponseEntity<>(bookMapper.mapTo(book), HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping(PATH + "/{id}")
  public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO book, @PathVariable Long id) {
    if (!bookService.isExists(id)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    BookEntity bookEntity = bookMapper.mapFrom(book);
    bookEntity.setId(id);
    BookEntity updatedBook = bookService.saveBook(bookEntity);
    return new ResponseEntity<>(bookMapper.mapTo(updatedBook), HttpStatus.OK);
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
