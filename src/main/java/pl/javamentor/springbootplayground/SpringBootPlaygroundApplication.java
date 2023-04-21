package pl.javamentor.springbootplayground;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javamentor.springbootplayground.example.domain.MyService;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootPlaygroundApplication {

    private final MyService myService;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootPlaygroundApplication.class, args);
        System.out.println("stop");
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("postConstruct is running");
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        System.out.println("postConstruct is done");
    }

}