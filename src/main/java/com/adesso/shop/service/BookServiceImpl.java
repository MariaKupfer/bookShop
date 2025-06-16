package com.adesso.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adesso.shop.BookNotFoundException;
import com.adesso.shop.model.Book;
import com.adesso.shop.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository; 

    BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
      return bookRepository.findById(id)
      .orElseThrow(() -> new BookNotFoundException(id));
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException(id);
        }
    }

    public Book updateBook(Book newBook, Long id){
        return bookRepository.findById(id)
      .map(Book -> {
        Book.setTitle(newBook.getTitle());
        Book.setAuthor(newBook.getAuthor());
        Book.setDescription(newBook.getDescription());
        Book.setIsbn(newBook.getIsbn());
        return bookRepository.save(Book);
      })
      .orElseGet(() -> {
        return bookRepository.save(newBook);
      });
    }
}
