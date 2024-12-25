package com.kosa.catalogservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kosa.catalogservice.domain.BookController;
import com.kosa.catalogservice.domain.BookNotFountException;
import com.kosa.catalogservice.domain.BookService;

import lombok.extern.slf4j.Slf4j;

import static org.mockito.BDDMockito.given;

@WebMvcTest(BookController.class)
@Slf4j
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "12121212";
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        log.info("isbn = " + isbn);
        // viewBookDetail() 호출시 BookNotFountException 예외가 발생 할 것으로 규정한다
        given(bookService.viewBookDetail(isbn)).willThrow(BookNotFountException.class);

        mockMvc
                .perform(get("/books/" + isbn)) // MockMvc는 HTTP GET 요청을 수행하고 결과를 확인할 수 있게 실행한다
                .andExpect(status().isNotFound());// 응답으로 404가 발생할 것으로 예상한다
    }

}
