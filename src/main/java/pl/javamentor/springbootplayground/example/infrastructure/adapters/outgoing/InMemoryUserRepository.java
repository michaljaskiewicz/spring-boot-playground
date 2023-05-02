package pl.javamentor.springbootplayground.example.infrastructure.adapters.outgoing;

import org.springframework.stereotype.Repository;
import pl.javamentor.springbootplayground.example.domain.User;
import pl.javamentor.springbootplayground.example.domain.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static com.google.common.base.Preconditions.checkArgument;

@Repository
class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> repo = new HashMap<>();

    private final AtomicLong nextId = new AtomicLong(1L);

    public InMemoryUserRepository() {
        create(new User("name1", null, null));
        create(new User("name2", null, null));
        create(new User("name3", null, null));
    }

    @Override
    public void create(final User user) {
        user.setId(nextId.getAndIncrement());
        repo.put(user.getId(), user);
    }

    @Override
    public void update(final User user) {
        checkArgument(user.getId() != null, "Cannot update user without id");
        checkArgument(repo.containsKey(user.getId()), "Cannot update user that does not exist");
        repo.put(user.getId(), user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(repo.get(userId));
    }

    @Override
    public void delete(Long userId) {
        repo.remove(userId);
    }

}