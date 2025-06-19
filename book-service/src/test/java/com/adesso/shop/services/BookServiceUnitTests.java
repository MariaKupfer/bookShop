package com.adesso.shop.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adesso.shop.TestDataUtil;
import com.adesso.shop.domain.Book;
import com.adesso.shop.repository.BookRepository;
import com.adesso.shop.service.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTests {
     
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Test
    public void testThatBookCanBeCreated(){
        Book newBook = TestDataUtil.createTestBookA();
        Book createdBookMock = newBook;
        createdBookMock.setId(1L);
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(createdBookMock);
        Book result = bookServiceImpl.saveBook(newBook);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(createdBookMock);
        assertThat(result.getId()).isGreaterThan(0);
    }

}
