package com.kosa.catalogservice.domain;

public class BookNotFountException extends RuntimeException {
    public BookNotFountException(String isbn) {
        super("The book with ISBN " + isbn + " was not found.");
    }
}
