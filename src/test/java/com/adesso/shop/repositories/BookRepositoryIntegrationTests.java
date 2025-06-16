package com.adesso.shop.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.adesso.shop.BookNotFoundException;
import com.adesso.shop.TestDataUtil;
import com.adesso.shop.domain.Book;
import com.adesso.shop.service.BookService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

    @Autowired
    private BookService underTest;

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Book book = TestDataUtil.createTestBookA();
        underTest.saveBook(book);
        Book result = underTest.getBookById(book.getId());
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Book bookA = TestDataUtil.createTestBookA();
        underTest.saveBook(bookA);
        bookA.setTitle("Agile Methods");
        underTest.updateBook(bookA, bookA.getId());
        Book result = underTest.getBookById(bookA.getId());
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Book book = TestDataUtil.createTestBookA();
        underTest.saveBook(book);
        underTest.deleteBook(book.getId());
        assertThatThrownBy(() -> underTest.getBookById(book.getId()))
            .isInstanceOf(BookNotFoundException.class)
            .hasMessageContaining(String.valueOf(book.getId()));
    }

    @Test
    public void testThatBookNotFoundThrowsException() {
    Long nonExistentId = 999L;
    assertThatThrownBy(() -> underTest.getBookById(nonExistentId))
        .isInstanceOf(BookNotFoundException.class)
        .hasMessageContaining("999");
    }

    @Test
    public void testFindByTitleReturnsBook() {
        Book book = TestDataUtil.createTestBookA();
        TestDataUtil.createTestBookB();
        book.setTitle("Clean Code");
        underTest.saveBook(book);
        List<Book> result = underTest.getBookByTitle("Clean Code");
        assertThat(result).isNotNull();
        assertThat(result).extracting(Book::getTitle)
                      .containsOnly(book.getTitle());
    }
}