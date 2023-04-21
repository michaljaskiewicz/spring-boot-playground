package pl.javamentor.springbootplayground.example.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MyService {

    private final MyRepository myRepository;

    private int counter = 0;

    public void doSomething() {
        counter++;
        System.out.println("I did something " + counter + " times");
    }

    public List<MyEntity> findAll() {
        return myRepository.findAll();
    }

}
