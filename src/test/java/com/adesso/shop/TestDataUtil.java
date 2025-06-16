package com.adesso.shop;

import com.adesso.shop.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){

    }

public static Book createTestBookA() {
    return Book.builder()
            .title("Clean Code")
            .author("Robert C. Martin")
            .description("A Handbook of Agile Software Craftsmanship")
            .isbn("9780132350884")
            .build();
}

public static Book createTestBookB() {
    return Book.builder()
            .title("Effective Java")
            .author("Joshua Bloch")
            .description("Best practices for the Java platform")
            .isbn("9780134685991")
            .build();
}
}
