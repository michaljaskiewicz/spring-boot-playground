package pl.javamentor.springbootplayground.user.infrastructure.adapters.incoming.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateUserRestInvoker {

    private final TestRestTemplate restTemplate;

    private static final String URL = "/api/users";

    public ResponseEntity<UserDetailsResponseDto> createUser(final CreateUserDto createUserDto) {
        return restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(createUserDto), UserDetailsResponseDto.class);
    }

    public <T> ResponseEntity<T> createUser(final CreateUserDto createUserDto, final Class<T> responseType) {
        return restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(createUserDto), responseType);
    }

    @Data
    @AllArgsConstructor
    public static class CreateUserDto {
        private String name;
        private String lifeStoryDescription;
        private List<String> hobbies;
    }

    @Data
    @AllArgsConstructor
    public static class UserDetailsResponseDto {
        private String name;
        private String lifeStoryDescription;
        private List<String> hobbies;
    }

}
