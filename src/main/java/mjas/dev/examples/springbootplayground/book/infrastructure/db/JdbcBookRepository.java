package mjas.dev.examples.springbootplayground.book.infrastructure.db;

import lombok.extern.slf4j.Slf4j;
import mjas.dev.examples.springbootplayground.book.domain.Book;
import mjas.dev.examples.springbootplayground.book.domain.BookRepository;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class JdbcBookRepository implements BookRepository {

    public JdbcBookRepository() {
        log.info("Creating JdbcBookRepository");
    }

    @Override
    public void create(Book book) {
        throw new UnsupportedOperationException("not implemented");
    }
}
