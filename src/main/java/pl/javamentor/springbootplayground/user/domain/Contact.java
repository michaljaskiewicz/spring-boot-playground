package pl.javamentor.springbootplayground.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

	@Id
	@GeneratedValue
	private Long id;

	private Long userId;

	@Enumerated(EnumType.STRING)
	private ContactType type;

	private String text;

	enum ContactType {
		PHONE, EMAIL
		}

}
