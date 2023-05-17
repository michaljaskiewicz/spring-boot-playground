package pl.javamentor.springbootplayground.example.domain.model.query;

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

    public Optional<String> getName() {
        return ofNullable(name);
    }

    public Optional<String> getHobby() {
        return ofNullable(hobby);
    }

    public Optional<String> getLifeStoryDescription() {
        return ofNullable(lifeStoryDescription);
    }
}
