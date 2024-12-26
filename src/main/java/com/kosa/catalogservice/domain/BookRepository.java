package com.kosa.catalogservice.domain;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    // Iterable<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    // Book save(Book book);
    @Modifying // 데이터 베이스 상태를 수정할 연산임을 나타낸다
    @Transactional // 메서드가 트랜젝션으로 실행됨을 나타낸다
    @Query("delete from book where isbn = :isbn ") // 스프링 데이터가 메서드 구현에 사용할 쿼리를 선언한다
    void deleteByIsbn(String isbn);
}
