package tim10.project.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSchemaException extends RuntimeException {

    public InvalidSchemaException(String message) {super(message);}

    public InvalidSchemaException() {
        super("Invalid schema of the uploaded file.");
    }
}
