package mjas.dev.examples.springbootplayground.book.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Book {
    private final UUID id;
    private final String title;
    private final String author;

    public static Book create(UUID id, String title, String author) {
        return new Book(id, title, author);
    }

    public UUID getId() {
        return id;
    }
}
