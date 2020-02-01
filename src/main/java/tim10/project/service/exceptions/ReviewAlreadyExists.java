package tim10.project.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReviewAlreadyExists extends RuntimeException{

    public ReviewAlreadyExists(String message) {super(message);}

    public ReviewAlreadyExists() {
        super("Review with that name already exists.");
    }
}
