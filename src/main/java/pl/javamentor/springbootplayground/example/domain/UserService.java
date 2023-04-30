package pl.javamentor.springbootplayground.example.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserService {

    private final UserRepository userRepository;

    private int counter = 0;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void doSomething() {
        counter++;
        System.out.println("I did something " + counter + " times");
    }

    public Long createUser(String name) {
        User user = new User(name);
        userRepository.create(user);
        return user.getId();
    }
}
