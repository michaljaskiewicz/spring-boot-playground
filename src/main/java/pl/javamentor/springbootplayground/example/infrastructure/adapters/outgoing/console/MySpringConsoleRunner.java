package pl.javamentor.springbootplayground.example.infrastructure.adapters.outgoing.console;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import pl.javamentor.springbootplayground.example.domain.UserService;

//@Component
@RequiredArgsConstructor
public class MySpringConsoleRunner implements CommandLineRunner {

    public final UserService myService;

    @Override
    public void run(String... args) throws Exception {
//        myService.findAll().forEach(System.out::println);
        System.out.println("MySpringConsoleRunner is running");
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        myService.doSomething();
        System.out.println("MySpringConsoleRunner is done");
    }

}
