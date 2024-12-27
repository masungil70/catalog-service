package com.kosa.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.kosa.catalogservice.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("intergration")
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = Book.of("1234567891", "책 제목", "책 저자", 9.90);

		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange() // url을 요청한다
				.expectStatus().isCreated() // 응답 코드가 201번(HttpStatus.CREATED) 인지 확인한다
				.expectBody(Book.class).value(actualBook -> {
					assertThat(actualBook).isNotNull(); // http 응답의 본문이 null값이 아닌지 확인한다
					assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn()); // 실제객체의 isbn과 예상 isbn의 값이 같은지 확인한다
				});
	}

}
