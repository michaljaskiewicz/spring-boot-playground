package pl.javamentor.springbootplayground.example.infrastructure.adapters.outgoing;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javamentor.springbootplayground.example.domain.User;

public interface SpringDataJpaRepository extends JpaRepository<User, Long> {
}
