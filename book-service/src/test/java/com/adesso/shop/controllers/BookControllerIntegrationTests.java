package com.adesso.shop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.adesso.shop.TestDataUtil;
import com.adesso.shop.domain.BookEntity;
import com.adesso.shop.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private BookService bookService;
    private static final String PATH = "/v1/api/books";


    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService){
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.bookService = bookService;
    }
    
    @Test
    public void testThatCreateBookSuccesfullyReturnsHttp201Created() throws Exception{
        BookEntity book = TestDataUtil.createTestBookA();
        book.setId(null);
        String bookJSON = objectMapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        );     
    }

    @Test
    public void testThatCreateBookSuccesfullyReturnsSavedBook() throws Exception{
        BookEntity book = TestDataUtil.createTestBookA();
        book.setId(null);
        String bookJSON = objectMapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.description").value(book.getDescription())
        );     
    }

    @Test
    public void testThatListOfAllBooksReturnsHttpStatus200() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());  
    }

    @Test
    public void testThatListOfAllBooksReturnsListOfBooks() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].title").value("Clean Code")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].isbn").value("9780132350884")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].author").value("Robert C. Martin")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].description").value("A Handbook of Agile Software Craftsmanship")
        );;  
    }

    @Test
    public void testThatFindBookByIdReturnsHttpStatus200() throws Exception{
        BookEntity book = TestDataUtil.createTestBookA();
        BookEntity createdBook = bookService.saveBook(book);
        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + createdBook.getId())
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());  
    }

    @Test
    public void testThatFindBookByIdReturnsHttpStatus404() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/99")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());  
    }

    @Test
    public void testThatFindBookByIdSuccesfullyReturnsBookById() throws Exception{
        BookEntity book = TestDataUtil.createTestBookA();
        BookEntity createdBook = bookService.saveBook(book);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + createdBook.getId())
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.description").value(book.getDescription())
        );     
    }

    @Test
    public void testThatUpdateBookdReturnsHttpStatus200() throws Exception{
        BookEntity book = TestDataUtil.createTestBookA();
        BookEntity createdBook = bookService.saveBook(book);
        createdBook.setAuthor("Maria Kupfer");
        String bookJSON = objectMapper.writeValueAsString(createdBook);
        mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/" + createdBook.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookJSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());  
    }

    @Test
    public void testThatUpdateBookSuccesfullyReturnsBookById() throws Exception{
        BookEntity book = TestDataUtil.createTestBookA();
        BookEntity createdBook = bookService.saveBook(book);
        createdBook.setAuthor("Maria Kupfer");
        String bookJSON = objectMapper.writeValueAsString(createdBook);
        mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/" + createdBook.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value(createdBook.getTitle())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").value(createdBook.getIsbn())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.author").value(createdBook.getAuthor())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.description").value(createdBook.getDescription())
        );     
    }

    @Test
    public void testThatDeleteBookReturnsHttpStatus204() throws Exception{
        BookEntity book = TestDataUtil.createTestBookA();
        BookEntity createdBook = bookService.saveBook(book);
        mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/" + createdBook.getId())
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());  
    }

    @Test
    public void testThatDeleteBookReturnsHttpStatus404() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/999")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());  
    }
}
