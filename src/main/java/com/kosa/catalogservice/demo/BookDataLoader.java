package com.kosa.catalogservice.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kosa.catalogservice.domain.Book;
import com.kosa.catalogservice.domain.BookRepository;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.save(Book.of("1234567891", "책 제목1", "책 저자1", 9.90));
        bookRepository.save(Book.of("1234567892", "책 제목2", "책 저자2", 19.90));
    }
}
