package com.adesso.shop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.adesso.shop.BookNotFoundException;
import com.adesso.shop.domain.Book;
import com.adesso.shop.domain.OrderEvent;
import com.adesso.shop.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository; 

    BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return StreamSupport.stream(bookRepository
                      .findAll()
                      .spliterator(), false)
                  .collect(Collectors.toList());
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
      return bookRepository.findById(id);
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
  public List<Book> getBookByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  @Override
  public boolean isExists(Long id) {
   return bookRepository.existsById(id);
  }

@KafkaListener(topics = "order.created", groupId = "product-group")
public void handleOrderCreated(OrderEvent event) {
    Long bookId = Long.valueOf(event.getProductId());
    Optional<Book> optionalBook = getBookById(bookId);

    if (optionalBook.isPresent()) {
        Book book = optionalBook.get();
        book.setInStock(false);
        bookRepository.save(book);
    } else {
        // Logging oder Fehlerbehandlung
        System.out.println("Book with ID {} not found – cannot mark as out of stock" +  bookId);
    }
}
}
