package pl.javamentor.springbootplayground.jpaplayground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("select b from Book b join fetch b.authors")
	List<Book> findAllEager();

	@Query("select b from Book b join fetch b.authors where b.id = :id")
	Optional<Book> findByIdEager(@Param("id") Long id);
}
