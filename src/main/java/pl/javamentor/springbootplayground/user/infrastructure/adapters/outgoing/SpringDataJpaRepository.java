package pl.javamentor.springbootplayground.user.infrastructure.adapters.outgoing;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javamentor.springbootplayground.user.domain.User;

public interface SpringDataJpaRepository extends JpaRepository<User, Long> {
}
