package com.adesso.shop.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.adesso.shop.domain.BookEntity;
import com.adesso.shop.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository; 

    BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return StreamSupport.stream(bookRepository
                      .findAll()
                      .spliterator(), false)
                  .collect(Collectors.toList());
    }

    @Override
    public BookEntity saveBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
      return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

  @Override
  public List<BookEntity> getBookByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  @Override
  public boolean isExists(Long id) {
   return bookRepository.existsById(id);
  }

@KafkaListener(topics = "order.created", groupId = "my-consumer-group")
public void handleOrderCreated(String message) {
    Long bookId = Long.valueOf(message);
    Optional<BookEntity> optionalBook = getBookById(bookId);

    if (optionalBook.isPresent()) {
        System.out.println(bookId);
        BookEntity book = optionalBook.get();
        book.setInStock(false);
        bookRepository.save(book);
    } else {
        // Logging oder Fehlerbehandlung
        System.out.println("Book with ID {} not found – cannot mark as out of stock" +  bookId);
    }
}
}
