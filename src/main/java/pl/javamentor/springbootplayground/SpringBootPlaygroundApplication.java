package pl.javamentor.springbootplayground;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class SpringBootPlaygroundApplication {

	private final JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPlaygroundApplication.class, args);
	}

	@PostConstruct
	void init() {
		jdbcTemplate.execute("INSERT INTO team VALUES (nextval('team_seq'), 'Champions');");
		jdbcTemplate.execute("INSERT INTO team VALUES (nextval('team_seq'), 'Programmers');");
		jdbcTemplate.execute("INSERT INTO team VALUES (nextval('team_seq'), 'Whatever');");
	}

}
