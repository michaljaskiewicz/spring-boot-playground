package pl.javamentor.springbootplayground.user.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.javamentor.springbootplayground.commons.domain.model.exceptions.DomainObjectNotFoundException;
import pl.javamentor.springbootplayground.user.domain.model.query.FindUsersFilter;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserService {

	private final UserRepository userRepository;

	private int counter = 0;

	public List<User> findAll(final FindUsersFilter filter) {
		return userRepository.findAll(filter);
	}

	public void doSomething() {
		counter++;
		System.out.println("I did something " + counter + " times");
	}

	public Long createUser(String name, String lifeStoryDescription, List<String> hobbies) {
		User user = new User(name, lifeStoryDescription, hobbies);
		userRepository.create(user);
		return user.getId();
	}

	public User getById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new DomainObjectNotFoundException("User with id " + userId + " not found"));
	}

	public void updateUser(Long userId, String lifeStoryDescription, List<String> hobbies) {
		User user = getById(userId);
		user.update(lifeStoryDescription, hobbies);
		userRepository.update(user);
	}

	public void deleteById(Long userId) {
		userRepository.delete(userId);
	}
}
