package pl.javamentor.springbootplayground.example.infrastructure.adapters.outgoing;

import org.springframework.stereotype.Repository;
import pl.javamentor.springbootplayground.example.domain.User;
import pl.javamentor.springbootplayground.example.domain.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> repo = new HashMap<>();

    private final AtomicLong nextId = new AtomicLong(1L);

    public InMemoryUserRepository() {
        create(new User("name1"));
        create(new User("name2"));
        create(new User("name3"));
    }

    @Override
    public void create(final User user) {
        user.setId(nextId.getAndIncrement());
        repo.put(user.getId(), user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(repo.values());
    }

}