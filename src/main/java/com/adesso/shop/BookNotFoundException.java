package com.adesso.shop;

public class BookNotFoundException extends RuntimeException {

    private final Long bookId;

    public BookNotFoundException(Long bookId) {
        super("Could not find book with id " + bookId);
        this.bookId = bookId;
    }

    public BookNotFoundException(String message) {
        super(message);
        this.bookId = null;
    }

    public Long getBookId() {
        return bookId;
    }
}

