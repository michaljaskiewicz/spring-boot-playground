package pl.javamentor.springbootplayground.user.infrastructure.adapters.outgoing;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import pl.javamentor.springbootplayground.user.domain.User;
import pl.javamentor.springbootplayground.user.domain.UserRepository;
import pl.javamentor.springbootplayground.user.domain.model.query.FindUsersFilter;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

	private final SpringDataJpaRepository springDataJpaRepository;

	@Override
	public void create(User user) {
		springDataJpaRepository.save(user);
	}

	@Override
	public void update(User user) {
		springDataJpaRepository.save(user);
	}

	@Override
	public List<User> findAll(FindUsersFilter filter) {
		if (filter.getLifeStoryDescription().isEmpty() && filter.getHobby().isEmpty() && filter.getName().isEmpty()) {
			return springDataJpaRepository.findAll();
		}
		throw new NotImplementedException("Cannot filter");
	}

	@Override
	public Optional<User> findById(Long userId) {
		return springDataJpaRepository.findById(userId);
	}

	@Override
	public void delete(Long userId) {
		springDataJpaRepository.findById(userId)
				.ifPresent(springDataJpaRepository::delete);
	}
}
