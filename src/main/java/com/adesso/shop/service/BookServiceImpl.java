package com.adesso.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adesso.shop.BookNotFoundException;
import com.adesso.shop.domain.Book;
import com.adesso.shop.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository; 

    BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
      return bookRepository.findById(id)
      .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException(id);
        }
    }

    @Override
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

  @Override
  public List<Book> getBookByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

}
