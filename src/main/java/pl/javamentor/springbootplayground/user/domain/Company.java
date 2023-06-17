package pl.javamentor.springbootplayground.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.javamentor.springbootplayground.user.domain.model.Address;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Company {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Address address;

	public Company(final String name, final Address address) {
		this.name = name;
		this.address = address;
	}
}
