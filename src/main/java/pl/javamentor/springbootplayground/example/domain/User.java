package pl.javamentor.springbootplayground.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nullable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.ZoneId.systemDefault;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@Table(name = "app_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private Long id;

    private Instant createdAt;

    private LocalDateTime registeredAt;

    private String name;

    private String lifeStoryDescription;

    private List<String> hobbies;

    private Instant modifiedAt;

    public User(String name, @Nullable String lifeStoryDescription, @Nullable List<String> hobbies) {
        checkArgument(isNotBlank(name), "Cannot create user with blank name");
        this.createdAt = Instant.now();
        this.registeredAt = LocalDateTime.ofInstant(createdAt, systemDefault());
        this.modifiedAt = createdAt;
        this.name = name;
        this.lifeStoryDescription = lifeStoryDescription;
        this.hobbies = ofNullable(hobbies).orElse(new ArrayList<>());
    }

    public Optional<String> getLifeStoryDescription() {
        return Optional.ofNullable(lifeStoryDescription);
    }

    public void update(String lifeStoryDescription, List<String> hobbies) {
        this.lifeStoryDescription = lifeStoryDescription;
        this.hobbies = ofNullable(hobbies).orElse(new ArrayList<>());
        this.modifiedAt = Instant.now();
    }
}
