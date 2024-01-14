package pl.javamentor.springbootplayground.jpaplayground;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {

	private final BookRepository repository;
	private final EntityManager entityManager;
	private final Session session;
	private final TransactionTemplate transactionTemplate;

	@Transactional
	public Long create(final Book book) {
		final Book saved = repository.save(book);
		return saved.getId();
	}

	@Transactional
	public void update(final Book book, final Long id) {
		final Book oldBook = repository.findById(id).orElseThrow();
		//		book.getAuthors().stream().map(Author::getId).map(authorRepo::findById)
		oldBook.setAll(book);
		repository.save(oldBook);
	}

	@Transactional
	public void remove(final Long id) {
		repository.deleteById(id);
	}

	public Book get(final Long id) {
		final Optional<Book> byId = repository.findByIdEager(id);
		return byId.orElseThrow();
	}

	public List<Book> findAll() {
		final List<Book> all = repository.findAllEager();
		return all;
	}
	//
	//	@PostConstruct
	//	public void afterCreate() {
	//		transactionTemplate.execute((it) -> {
	//			entityManager.persist(Author.builder().name("Tolkien").build());
	//			entityManager.persist(Author.builder().name("Rowling").build());
	//			entityManager.persist(Author.builder().name("Other").build());
	//			return it;
	//		});
	//	}
}
