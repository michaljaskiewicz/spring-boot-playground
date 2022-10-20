package mjas.dev.examples.springbootplayground.book.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        log.info("Creating BookService with repo = {}", bookRepository.getClass());
    }

    public String addBook(String title, String author) {
        Book book = Book.create(bookRepository.generateId(), title, author);
        bookRepository.create(book);
        return book.getId().toString();
    }

}