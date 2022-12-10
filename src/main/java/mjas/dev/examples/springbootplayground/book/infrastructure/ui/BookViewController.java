package mjas.dev.examples.springbootplayground.book.infrastructure.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mjas.dev.examples.springbootplayground.book.domain.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookViewController {

    private final BookService bookService;

    @GetMapping("/")
    public String homePage(Model model) {
        log.info("GET homePage");
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("newBook", new BookDTO());
        return "home";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute("newBook") BookDTO book) { // TODO DTO
        log.info("POST book from html form: {}", book);
        bookService.addBook(book.title, book.author);
        return "redirect:/";
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookDTO {
        String title;
        String author;
    }
}
