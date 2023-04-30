package pl.javamentor.springbootplayground.example.infrastructure.adapters.incoming.rest;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.javamentor.springbootplayground.example.domain.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<?> create(@RequestBody final CreateUserDto createUserDto) {
        try {
            Long createdUserId = userService.createUser(createUserDto.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/users")
    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getName()))
                .toList();
    }

    @Data
    private static class CreateUserDto {
        private String name;
    }

    @Value
    private static class UserDto {
        Long id;
        String name;
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
