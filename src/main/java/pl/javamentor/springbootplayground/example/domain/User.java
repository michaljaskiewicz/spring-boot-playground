package pl.javamentor.springbootplayground.example.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class User {

    @Setter
    private Long id;

    private final String name;

    public User(String name) {
        checkArgument(isNotBlank(name), "Cannot create user with blank name");
        this.name = name;
    }
}
