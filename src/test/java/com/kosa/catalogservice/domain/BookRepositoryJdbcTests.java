package com.kosa.catalogservice.domain;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.kosa.catalogservice.config.DataConfig;

@DataJdbcTest // 스프림 데이터 JDBC 컨퍼넌트를 집중적으로 테스트하는 클래스임을 나타낸다
@Import(DataConfig.class) // 데이터 설정을 임포트한다(감사를 활성화 하기위해 필요)
@AutoConfigureTestDatabase( // 테스트 컨테이너를 이용해야 하기 때문에 내장 테스트 데이터 베이스 사용을 비활성화 한다
        replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("intergration") // application-intergration.yml 설정 파일을 활성화 한다
public class BookRepositoryJdbcTests {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate; // 데이터베이스와 상호작용을 하기 위한 하위 수준의 객체

    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234561237";
        var book = Book.of(bookIsbn, "제목", "저장", 12.90);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }
}
