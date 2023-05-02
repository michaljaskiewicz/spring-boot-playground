package pl.javamentor.springbootplayground.example.infrastructure.adapters.incoming.rest;

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
import org.springframework.web.bind.annotation.RestController;
import pl.javamentor.springbootplayground.commons.domain.model.exceptions.DomainObjectNotFoundException;
import pl.javamentor.springbootplayground.example.domain.User;
import pl.javamentor.springbootplayground.example.domain.UserService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<?> create(@RequestBody final CreateUserDto createUserDto) {
        try {
            Long createdUserId = userService.createUser(
                    createUserDto.getName(), createUserDto.getLifeStoryDescription(), createUserDto.getHobbies());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // TODO: filter by name (show query params)
    @GetMapping("/api/users")
    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getRegisteredAt()))
                .toList();
    }

    @GetMapping("/api/users/{userId}")
    public UserDetailsDto findById(@PathVariable Long userId) {
        return toUserDetailsDto(userService.getById(userId));
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity delete(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
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

    private UserDetailsDto toUserDetailsDto(User user) {
        return new UserDetailsDto(
                user.getId(),
                user.getName(),
                user.getRegisteredAt(),
                user.getModifiedAt(),
                user.getLifeStoryDescription().orElse("no life story"),
                user.getHobbies());
    }

    @Data
    private static class CreateUserDto {
        private String name;
        private String lifeStoryDescription;
        private List<String> hobbies;
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

    @Value
    private static class UserDetailsDto {
        Long id;
        String name;
        LocalDateTime registeredAt;
        Instant modifiedAt;;
        String lifeStoryDescription;
        List<String> hobbies;
    }

    /*
     * CRUD:
     * Create - POST
     * Read - GET
     * Update - PUT
     * Delete - DELETE
     * */

    // REST API:
    // - przeglądarka - aplikacja frontendowa - inne technologie niż Java (np. React, Angular, Vue)
    // - aplikacja mobilna - Android, iOS
    // - aplikacja desktopowa
    // - inna aplikacja backendowa

    // HTTP
    // - synchroniczna komunikacja klient-serwer
    // - klient wysyła żądanie do serwera
    // - żądanie czeka/wisi aż serwer odpowie
    // - serwer odpowiada na żądanie klienta
    // protokół bez stanowy (stateless)

    // HTTP - metody
    // - GET - pobranie danych
    // - POST - wysłanie danych
    // - PUT - aktualizacja danych


    // proxy -> serwer_1
    //       -> serwer_2
    //       -> serwer_3
}
