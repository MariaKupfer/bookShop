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
import com.adesso.shop.domain.BookEntity;
import com.adesso.shop.repositories.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTests {
     
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Test
    public void testThatBookCanBeCreated(){
        BookEntity newBook = TestDataUtil.createTestBookA();
        BookEntity createdBookMock = newBook;
        createdBookMock.setId(1L);
        when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(createdBookMock);
        BookEntity result = bookServiceImpl.saveBook(newBook);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(createdBookMock);
        assertThat(result.getId()).isGreaterThan(0);
    }

}
