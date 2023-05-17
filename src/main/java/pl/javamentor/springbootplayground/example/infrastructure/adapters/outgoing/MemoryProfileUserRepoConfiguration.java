package pl.javamentor.springbootplayground.example.infrastructure.adapters.outgoing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pl.javamentor.springbootplayground.example.domain.UserRepository;

@Configuration
@Profile("memory")
public class MemoryProfileUserRepoConfiguration {

    @Bean
    @Primary
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

}
