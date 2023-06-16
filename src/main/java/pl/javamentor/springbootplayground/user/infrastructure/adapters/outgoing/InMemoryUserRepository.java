package pl.javamentor.springbootplayground.user.infrastructure.adapters.outgoing;

import lombok.extern.slf4j.Slf4j;
import pl.javamentor.springbootplayground.user.domain.User;
import pl.javamentor.springbootplayground.user.domain.UserRepository;
import pl.javamentor.springbootplayground.user.domain.model.query.FindUsersFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
class InMemoryUserRepository implements UserRepository {

	private final Map<Long, User> repo = new HashMap<>();

	private final AtomicLong nextId = new AtomicLong(1L);

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
	public List<User> findAll(FindUsersFilter filter) {
		return repo.values().stream()
				.filter(user -> filter.getHobby().isEmpty() || user.getHobbies().stream().anyMatch(hobby -> hobby.toLowerCase().contains(filter.getHobby().get().toLowerCase())))
				.filter(user -> filter.getName().isEmpty() || user.getName().toLowerCase().contains(filter.getName().get().toLowerCase()))
				.filter(user -> userLifeStoryContainsValueFromFilter(filter, user))
				.toList();
	}

	private boolean userLifeStoryContainsValueFromFilter(FindUsersFilter filter, User user) {
		if (filter.getLifeStoryDescription().isEmpty()) {
			return true;
		}
		return user.getLifeStoryDescription().isPresent()
				&& user.getLifeStoryDescription().get().toLowerCase().contains(filter.getLifeStoryDescription().get().toLowerCase());
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
