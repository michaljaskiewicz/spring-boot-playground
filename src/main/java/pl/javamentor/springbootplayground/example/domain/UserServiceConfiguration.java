package pl.javamentor.springbootplayground.example.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

}
