package pl.javamentor.springbootplayground.jpaplayground;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book extends BaseEntity {

	private String name;

	@Singular
	@JsonIgnoreProperties("books")
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable
	private Set<Author> authors;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bookId")
	private Set<BookChapter> bookChapters;

	@Version
	private long version;

	void setAll(final Book book) {
		name = book.getName();
		// TODO: remove this from old authors
		authors = book.getAuthors();
		authors.forEach(author -> author.addBook(this));
	}

}
