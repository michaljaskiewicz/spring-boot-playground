package pl.javamentor.springbootplayground.jpaplayground;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BookChapter {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Long bookId;

}
