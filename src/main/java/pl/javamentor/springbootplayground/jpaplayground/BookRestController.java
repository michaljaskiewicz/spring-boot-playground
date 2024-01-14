package pl.javamentor.springbootplayground.jpaplayground;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookRestController {

	private final BookService bookService;

	@PostMapping("/books")
	Long create(@RequestBody Book book) {
		log.info("Creating book");
		final Long id = bookService.create(book);
		log.info("Created book with id {}", id);
		return id;
	}

	@PutMapping("/books/{id}")
	void update(@RequestBody Book book, @PathVariable("id") Long id) {
		log.info("Updating book with id {}", id);
		bookService.update(book, id);
		log.info("Updated book");
	}

	@DeleteMapping("/books/{id}")
	void remove(@PathVariable("id") Long id) {
		log.info("Removing book with id {}", id);
		bookService.remove(id);
		log.info("Removed book");
	}

	@GetMapping("/books/{id}")
	Book findById(@PathVariable("id") Long id) {
		final Book book = bookService.get(id);
		return book;
	}

	@GetMapping("/books")
	List<Book> findAll() {
		final List<Book> all = bookService.findAll();
		return all;
	}

}
