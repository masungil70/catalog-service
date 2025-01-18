package com.kosa.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.kosa.catalogservice.domain.Book;

@JsonTest
public class BookJsonTests {
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = Book.of("1234567890", "책 제목", "책 저자", 9.90, "출판사");
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathStringValue("$.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("$.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("$.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("$.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                    {
                        "isbn": "1234567890",
                        "title": "책 제목",
                        "author": "책 저자",
                        "price": 9.90,
                        "publisher": "출판사"
                    }
                """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Book.of("1234567890", "책 제목", "책 저자", 9.90, "출판사"));
    }
}
