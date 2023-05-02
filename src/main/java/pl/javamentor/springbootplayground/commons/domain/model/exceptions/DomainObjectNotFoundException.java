package pl.javamentor.springbootplayground.commons.domain.model.exceptions;

public class DomainObjectNotFoundException extends RuntimeException {

    public DomainObjectNotFoundException(String message) {
        super(message);
    }
}
