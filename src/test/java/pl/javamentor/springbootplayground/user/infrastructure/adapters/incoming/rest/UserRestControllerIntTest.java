package pl.javamentor.springbootplayground.user.infrastructure.adapters.incoming.rest;

import lombok.Value;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.javamentor.springbootplayground.user.infrastructure.adapters.incoming.rest.CreateUserRestInvoker.CreateUserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

// E2E
@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserRestControllerIntTest {


    private static final String API_USERS = "/api/users";

    @Autowired
    CreateUserRestInvoker createUserRest;

    @Autowired
    private TestRestTemplate restTemplate;

    // mockMVC

    @Test
    void should_create_user_and_get_list_of_all_users() {
        // when
        ResponseEntity<Long> createUserResponse = createUserRest.createUser(createAnyUser(), Long.class);

        // then
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long createdUserId = createUserResponse.getBody();
        assertThat(createdUserId).isGreaterThan(0L);

        // when
        ResponseEntity<List<UserListItemDto>> getAllResponse = restTemplate.exchange(API_USERS, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });

        // then
        assertThat(getAllResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getAllResponse.getBody()).extracting(UserListItemDto::getId).contains(createdUserId);
    }

    @Test
    void should_create_user_and_get_details_about_him() {
        // when
        CreateUserDto userToCreate = createAnyUser();
        ResponseEntity<Integer> createUserResponse = createUserRest.createUser(userToCreate, Integer.class);

        // then
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Integer createdUserId = createUserResponse.getBody();
        assertThat(createdUserId).isGreaterThan(0);

        // when
        ResponseEntity<Map<Object, Object>> getDetailsResponse = restTemplate.exchange(API_USERS + "/" + createdUserId,
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        // then
        assertThat(getDetailsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getDetailsResponse.getBody()).satisfies(userDetails -> {
            assertThat(userDetails.get("id")).isEqualTo(createdUserId);
            assertThat(userDetails.get("name")).isEqualTo(userToCreate.getName());
            assertThat(userDetails.get("registeredAt")).isNotNull();
            assertThat(userDetails.get("modifiedAt")).isNotNull();
            assertThat(userDetails.get("lifeStoryDescription")).isEqualTo(userToCreate.getLifeStoryDescription());
            assertThat((List<String>) userDetails.get("hobbies")).containsAll(userToCreate.getHobbies());
        });
    }


    private CreateUserDto createAnyUser() {
        return new CreateUserDto(
                "Dominik",
                "wanna be a programmer, while riding my bike",
                List.of("interviews"));
    }

    @Value
    private static class UserListItemDto {
        Long id;
        String name;
        LocalDateTime registeredAt;
    }
}
