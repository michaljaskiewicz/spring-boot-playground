package pl.javamentor.springbootplayground.jpaplayground;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@ToString
public class BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String uuid = UUID.randomUUID().toString();

}


