package com.adesso.shop;

import com.adesso.shop.domain.BookEntity;

public class TestDataUtil {
    private TestDataUtil(){

    }

public static BookEntity createTestBookA() {
    return BookEntity.builder()
            .title("Clean Code")
            .author("Robert C. Martin")
            .description("A Handbook of Agile Software Craftsmanship")
            .isbn("9780132350884")
            .inStock(true)
            .build();
}

public static BookEntity createTestBookB() {
    return BookEntity.builder()
            .title("Effective Java")
            .author("Joshua Bloch")
            .description("Best practices for the Java platform")
            .isbn("9780134685991")
            .inStock(true)
            .build();
}
}
