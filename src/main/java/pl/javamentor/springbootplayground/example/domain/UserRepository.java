package pl.javamentor.springbootplayground.example.domain;

import java.util.List;

public interface UserRepository {

    void create(User user);

    List<User> findAll();

}
