package pl.javamentor.springbootplayground.example.domain;

import java.util.List;

public interface MyRepository {
    List<MyEntity> findAll();
}
