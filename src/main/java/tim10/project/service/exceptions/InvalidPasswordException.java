package tim10.project.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {super(message);}

    public InvalidPasswordException() {
        super("Invalid password.");
    }
}
