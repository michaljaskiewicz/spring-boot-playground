package pl.javamentor.springbootplayground.user.domain;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

	@Bean
	public UserService userService(UserRepository userRepository, TeamRepository teamRepository, final EntityManager entityManager) {
		return new UserService(userRepository, teamRepository, entityManager);
	}

}
