package pl.javamentor.springbootplayground.user.infrastructure.adapters.outgoing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.javamentor.springbootplayground.user.domain.User;

import java.util.List;

public interface SpringDataJpaRepository extends JpaRepository<User, Long> {

	List<User> findByLifeStoryDescriptionContainsIgnoreCase(String lifeStoryDescription);

	List<User> findByLifeStoryDescriptionContainsIgnoreCaseOrNameContainsIgnoreCase(String lifeStoryDescription, String name);

	List<User> findAllByTeams_nameContainsIgnoreCase(String teamName);

	@Query(
			"select u from User u join u.teams t"
					+ " where lower(t.name) like concat('%', lower(:teamName), '%')"
	)
	List<User> findByTeamName(@Param("teamName") String teamName);

}
