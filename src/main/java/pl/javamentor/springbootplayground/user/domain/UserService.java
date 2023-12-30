package pl.javamentor.springbootplayground.user.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.javamentor.springbootplayground.commons.domain.model.exceptions.DomainObjectNotFoundException;
import pl.javamentor.springbootplayground.user.domain.model.Address;
import pl.javamentor.springbootplayground.user.domain.model.Sex;
import pl.javamentor.springbootplayground.user.domain.model.query.FindUsersFilter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserService {

	private final UserRepository userRepository;
	private final TeamRepository teamRepository;

	private int counter = 0;

	public List<User> findAll(final FindUsersFilter filter) {
		return userRepository.findAll(filter);
	}

	public void doSomething() {
		counter++;
		System.out.println("I did something " + counter + " times");
	}

	public Long createUser(String name, Sex sex, List<Contact> contacts, Address address, String lifeStoryDescription, List<String> hobbies, final Company company,
			final Set<Long> teams) {
		User user = new User(name, sex, contacts, address, lifeStoryDescription, hobbies, company);
		final Set<Team> userTeams = ofNullable(teams).orElse(emptySet()).stream().map(this::getTeamById).collect(Collectors.toSet());
		user.setTeams(userTeams);
		userRepository.create(user);
		return user.getId();
	}

	private Team getTeamById(final Long id) {
		return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find team by id"));
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
