package pl.javamentor.springbootplayground.example.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServiceConfiguration {

    @Bean
    public MyService myService(MyRepository myRepository) {
        return new MyService(myRepository);
    }

}
