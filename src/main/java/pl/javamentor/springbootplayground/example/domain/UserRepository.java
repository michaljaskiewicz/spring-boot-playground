package pl.javamentor.springbootplayground.example.domain;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
}
