package pl.javamentor.springbootplayground.user.infrastructure.adapters.incoming.rest;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.javamentor.springbootplayground.commons.domain.model.exceptions.DomainObjectNotFoundException;
import pl.javamentor.springbootplayground.user.domain.Company;
import pl.javamentor.springbootplayground.user.domain.Contact;
import pl.javamentor.springbootplayground.user.domain.User;
import pl.javamentor.springbootplayground.user.domain.UserService;
import pl.javamentor.springbootplayground.user.domain.model.Address;
import pl.javamentor.springbootplayground.user.domain.model.Sex;
import pl.javamentor.springbootplayground.user.domain.model.query.FindUsersFilter;
import pl.javamentor.springbootplayground.user.domain.model.query.FindUsersFilter.FindUsersFilterBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserRestController {

	private final UserService userService;

	@PostMapping("/api/users")
	public ResponseEntity<?> create(@RequestBody final CreateUserDto createUserDto) {
		try {
			Long createdUserId = userService.createUser(
					createUserDto.getName(),
					createUserDto.getSex(),
					createUserDto.getContacts(),
					createUserDto.getAddress(),
					createUserDto.getLifeStoryDescription(),
					createUserDto.getHobbies(),
					createUserDto.getCompany(),
					createUserDto.getTeams()
			);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUserId);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/api/users")
	public List<UserDto> findAll(@RequestParam final Optional<String> name,
			@RequestParam final Optional<String> hobby,
			@RequestParam final Optional<String> lifeStoryDescription) {
		return userService.findAll(buildFilter(name, hobby, lifeStoryDescription))
				.stream()
				.map(user -> new UserDto(user.getId(), user.getName(),
						LocalDateTime.ofInstant(user.getCreatedAt(), ZoneId.systemDefault())))
				.toList();
	}

	private FindUsersFilter buildFilter(Optional<String> name, Optional<String> hobby, Optional<String> lifeStoryDescription) {
		final FindUsersFilterBuilder filterBuilder = FindUsersFilter.builder();
		name.ifPresent(filterBuilder::name);
		hobby.ifPresent(filterBuilder::hobby);
		lifeStoryDescription.ifPresent(filterBuilder::lifeStoryDescription);
		return filterBuilder.build();
	}

	@GetMapping("/api/users/{userId}")
	public User findById(@PathVariable Long userId) {
		final User user = userService.getById(userId);
		return user;
	}

	@DeleteMapping("/api/users/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long userId) {
		userService.deleteById(userId);
	}

	@PutMapping("/api/users/{userId}")
	public void updateUser(@PathVariable Long userId, @RequestBody final UpdateUserDto updateUserDto) {
		userService.updateUser(userId,
				updateUserDto.getLifeStoryDescription(), updateUserDto.getHobbies());
	}

	@ExceptionHandler(DomainObjectNotFoundException.class)
	public ResponseEntity<?> handleDomainObjectNotFoundException(DomainObjectNotFoundException e) {
		return ResponseEntity.notFound().build();
	}

	@Data
	private static class CreateUserDto {
		private String name;
		private Sex sex;
		private String lifeStoryDescription;
		private List<Contact> contacts;
		private Address address;
		private List<String> hobbies;
		private Set<Long> teams;
		private Company company;
	}

	@Data
	private static class UpdateUserDto {
		private String lifeStoryDescription;
		private List<String> hobbies;
	}

	@Value
	private static class UserDto {
		Long id;
		String name;
		LocalDateTime registeredAt;
	}

}
