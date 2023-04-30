package pl.javamentor.springbootplayground;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javamentor.springbootplayground.example.domain.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootPlaygroundApplication {

    private final UserService myService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlaygroundApplication.class, args);
    }

}