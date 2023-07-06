package pl.javamentor.springbootplayground.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@ToString
public class Team {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "teams")
	@JsonIgnoreProperties("teams")
	private Set<User> members;

}
