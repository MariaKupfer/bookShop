package com.adesso.shop.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.adesso.shop.TestDataUtil;
import com.adesso.shop.domain.BookEntity;
import com.adesso.shop.services.BookService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

    @Autowired
    private BookService underTest;

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        BookEntity book = TestDataUtil.createTestBookA();
        underTest.saveBook(book);
        Optional<BookEntity> result = underTest.getBookById(book.getId());
        assertThat(result).isPresent(); 
        assertThat(result.get()).isEqualTo(book); 
    }

    @Test
    public void testFindByTitleReturnsBook() {
        BookEntity book = TestDataUtil.createTestBookA();
        TestDataUtil.createTestBookB();
        book.setTitle("Clean Code");
        underTest.saveBook(book);
        List<BookEntity> result = underTest.getBookByTitle("Clean Code");
        assertThat(result).isNotNull();
        assertThat(result).extracting(BookEntity::getTitle)
                      .containsOnly(book.getTitle());
    }
}