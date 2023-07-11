package pl.javamentor.springbootplayground.user.domain.model.query;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Builder
@Value
public class FindUsersFilter {

	String name;
	String hobby;
	String lifeStoryDescription;
	String teamName;

	public Optional<String> getName() {
		return ofNullable(name);
	}

	public Optional<String> getHobby() {
		return ofNullable(hobby);
	}

	public Optional<String> getLifeStoryDescription() {
		return ofNullable(lifeStoryDescription);
	}

	public Optional<String> getTeamName() {
		return ofNullable(teamName);
	}
}
