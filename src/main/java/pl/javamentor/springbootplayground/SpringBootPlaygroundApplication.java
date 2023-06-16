package pl.javamentor.springbootplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPlaygroundApplication.class, args);
	}

}
