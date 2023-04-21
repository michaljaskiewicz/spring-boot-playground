package pl.javamentor.springbootplayground.example.infrastructure;

import pl.javamentor.springbootplayground.example.domain.MyService;
import pl.javamentor.springbootplayground.example.domain.MyServiceConfiguration;

public class MyConsoleApp {

    public static void main(String[] args) {
        InMemoryRepo myRepository = new InMemoryRepo("any");

        MyService myService = new MyServiceConfiguration().myService(myRepository);

        myService.findAll()
                .forEach(System.out::println);
    }
}
