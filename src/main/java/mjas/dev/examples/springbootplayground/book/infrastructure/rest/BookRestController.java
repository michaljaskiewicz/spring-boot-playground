package mjas.dev.examples.springbootplayground.book.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mjas.dev.examples.springbootplayground.book.domain.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class BookRestController {

    private final BookService bookService;
    private final String helloWorldText;
    private final ObjectMapper jsonMapper;
    private final XmlMapper xmlMapper;

    public BookRestController(
            BookService bookService,
            @Value("${spring-boot-playground.app.hello-world}") String helloWorldText,
            ObjectMapper objectMapper) {
        this.bookService = bookService;
        this.helloWorldText = helloWorldText;
        this.jsonMapper = objectMapper;
        this.xmlMapper = new XmlMapper();
    }

    @PostConstruct
    public void initialize() {
        bookService.addBook("Harry Potter", "JK Rowling");
        bookService.addBook("LOTR", "Tolkien");
        bookService.addBook("Clean code", "Uncle Bob");
    }

    @PostMapping("/api/books")
    private ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) throws Exception{
        log.info("Adding new book, title = {}, author = {}", bookDTO.title, bookDTO.author);
        String id = bookService.addBook(bookDTO.getTitle(), bookDTO.getAuthor());
        bookDTO.getComments().add("Ale beznadzieja");
        bookDTO.getComments().add("To był żart");
        log.info("json serialization test: {}", jsonMapper.writeValueAsString(bookDTO));
        log.info("xml serialization test: {}", xmlMapper.writeValueAsString(bookDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/api/hello")
    private String hello() {
        return helloWorldText;
    }

    @Data
    private static class BookDTO {
        String author;
        String title;
        List<String> comments = new ArrayList<>();
    }

}
