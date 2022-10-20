package mjas.dev.examples.springbootplayground.book.infrastructure.db;

import lombok.extern.slf4j.Slf4j;
import mjas.dev.examples.springbootplayground.book.domain.Book;
import mjas.dev.examples.springbootplayground.book.domain.BookRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Primary
@Profile("memory")
@Repository
@Slf4j
public class InMemoryBookRepository implements BookRepository {

    private final Map<UUID, Book> books = new HashMap<>();

    public InMemoryBookRepository() {
        log.info("Creating InMemoryBookRepository");
    }

    @Override
    public void create(Book book) {
        books.put(book.getId(), book);
    }
}
