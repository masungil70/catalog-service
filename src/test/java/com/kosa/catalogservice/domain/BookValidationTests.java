package com.kosa.catalogservice.domain;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = new Book("1234567890", "책 제목", "책저자", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    /*
     * 테스트 오류 용 함수
     * 
     * @Test
     * void whenIsbnDefinedButIncorrectThenValidationFails() {
     * var book = new Book("a234567890", "책 제목", "책저자", 9.90);
     * Set<ConstraintViolation<Book>> violations = validator.validate(book);
     * assertThat(violations).isEmpty();
     * }
     */
}
