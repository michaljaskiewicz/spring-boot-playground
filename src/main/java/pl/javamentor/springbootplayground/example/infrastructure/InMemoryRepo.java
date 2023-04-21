package pl.javamentor.springbootplayground.example.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pl.javamentor.springbootplayground.example.domain.MyEntity;
import pl.javamentor.springbootplayground.example.domain.MyRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
class InMemoryRepo implements MyRepository {

    private final Map<Long, MyEntity> repo = new HashMap<>();

    public InMemoryRepo(@Value("${some.property.filename}") String filename) {
        System.out.println("InMemoryRepo is created with filename: " + filename);
        repo.put(1L, new MyEntity(1L, "name1"));
        repo.put(2L, new MyEntity(2L, "name2"));
        repo.put(3L, new MyEntity(3L, "name3"));
    }

    @Override
    public List<MyEntity> findAll() {
        return new ArrayList<>(repo.values());
    }

}