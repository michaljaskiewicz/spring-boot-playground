package pl.javamentor.springbootplayground.user.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pl.javamentor.springbootplayground.user.domain.model.Address;
import pl.javamentor.springbootplayground.user.domain.model.Sex;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "AppUser")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	private Long id;

	@CreatedDate
	private Instant registeredAt;

	private String name;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	private String lifeStoryDescription;

	private List<String> hobbies;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private List<Contact> contacts;

	private Address address;

	@Setter
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	@ToString.Exclude
	private Set<Team> teams;

	@ManyToOne(cascade = CascadeType.ALL)
	private Company company;

	@LastModifiedDate
	private Instant modifiedAt;

	public User(String name, Sex sex, final List<Contact> contacts, Address address, @Nullable String lifeStoryDescription, @Nullable List<String> hobbies,
			final Company company) {
		checkArgument(isNotBlank(name), "Cannot create user with blank name");
		this.name = name;
		this.sex = sex;
		this.lifeStoryDescription = lifeStoryDescription;
		this.hobbies = ofNullable(hobbies).orElse(new ArrayList<>());
		this.address = address;
		this.contacts = contacts;
		this.company = company;
	}

	public Optional<String> getLifeStoryDescription() {
		return Optional.ofNullable(lifeStoryDescription);
	}

	public void update(String lifeStoryDescription, List<String> hobbies) {
		this.lifeStoryDescription = lifeStoryDescription;
		this.hobbies = ofNullable(hobbies).orElse(new ArrayList<>());
	}
}
