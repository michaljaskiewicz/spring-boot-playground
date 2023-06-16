package pl.javamentor.springbootplayground.user.domain;

import pl.javamentor.springbootplayground.user.domain.model.query.FindUsersFilter;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

	void create(User user);

	void update(User user);

	List<User> findAll(FindUsersFilter filter);

	Optional<User> findById(Long userId);

	void delete(Long userId);
}
