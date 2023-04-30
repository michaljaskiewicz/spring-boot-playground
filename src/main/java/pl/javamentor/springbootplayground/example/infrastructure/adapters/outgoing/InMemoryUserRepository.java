package pl.javamentor.springbootplayground.example.infrastructure.adapters.outgoing;

import org.springframework.stereotype.Repository;
import pl.javamentor.springbootplayground.example.domain.User;
import pl.javamentor.springbootplayground.example.domain.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> repo = new HashMap<>();

    public InMemoryUserRepository() {
        repo.put(1L, new User(1L, "name1"));
        repo.put(2L, new User(2L, "name2"));
        repo.put(3L, new User(3L, "name3"));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(repo.values());
    }

}