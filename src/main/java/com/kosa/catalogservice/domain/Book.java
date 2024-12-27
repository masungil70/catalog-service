package com.kosa.catalogservice.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
        @Id Long id,
        @NotBlank(message = "The book ISBN must be defined.") @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The ISBN format must be valid.") String isbn,

        @NotBlank(message = "The book title must be defined.") String title,

        @NotBlank(message = "The book author must be defined.") String author,

        @NotNull(message = "The book price must be defined.") @Positive(message = "The book price must be greater than zero.") Double price,

        String publisher,

        @CreatedDate Instant createdDate,

        @LastModifiedDate Instant LastModifiedDate,

        @Version int version) {
    public static Book of(String isbn, String title, String author, Double price, String publisher) {
        return new Book(null, isbn, title, author, price, publisher, null, null, 0);
    }
}

/*
 * 테스트 할 때
 * 
 * curl -v -X POST http://localhost:9091/books -H \
 * "Content-Type: application/json" \
 * -d '{"author": "aaa", "title": "bbb", "isbn": "12345678a91", "price": 9.90}'
 * 
 * (httpie 프로그램 설치 후)
 * http POST :9091/books author="aaa" title="bbb" isbn="12345678a91" price=9.90
 */