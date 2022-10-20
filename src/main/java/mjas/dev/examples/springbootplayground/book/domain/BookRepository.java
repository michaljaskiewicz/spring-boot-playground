package mjas.dev.examples.springbootplayground.book.domain;

import java.util.UUID;

public interface BookRepository {

    default UUID generateId() {
        return UUID.randomUUID();
    }

    void create(Book book);

}