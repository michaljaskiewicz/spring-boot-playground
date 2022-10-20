package mjas.dev.examples.springbootplayground.book.domain;

import lombok.Value;

@Value
public class Author {

    String firstName;
    String lastName;

    public Author(String firstName, String lastName) {
        if (firstName == null) {
            throw new IllegalArgumentException("cannot create without firstName");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

//
//    boolean isAuthorOfABook(Book book) {
//        return true;
//    }
}
