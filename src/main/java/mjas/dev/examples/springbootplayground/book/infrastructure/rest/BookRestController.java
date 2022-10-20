package mjas.dev.examples.springbootplayground.book.infrastructure.rest;

import lombok.extern.slf4j.Slf4j;
import mjas.dev.examples.springbootplayground.book.domain.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BookRestController {

    private final BookService bookService;
    private final String helloWorldText;

    public BookRestController(
            BookService bookService,
            @Value("${spring-boot-playground.app.hello-world}") String helloWorldText) {
        this.bookService = bookService;
        this.helloWorldText = helloWorldText;
    }

    @PostMapping("/api/books")
    private ResponseEntity<String> addBook(@RequestHeader(value = "api-version", required = false) String apiVersion,
                                           @RequestHeader(value = "Accept") String accept,
                                           @RequestParam("author") String author, @RequestParam("title") String title) {
        log.info("Got new request for api-version = {}", apiVersion);
        log.info("Adding new book, title = {}, author = {}", title, author);
        String id = bookService.addBook(title, author);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("received-accept", accept)
                .header("book-id", id)
                .body("Added book with id = " + id);
    }

    @GetMapping("/api/hello")
    private String hello() {
        return helloWorldText;
    }

}
