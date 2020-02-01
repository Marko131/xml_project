package tim10.project.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PaperAlreadyExists extends RuntimeException {

    public PaperAlreadyExists(String message) {super(message);}

    public PaperAlreadyExists() {
        super("Paper with that name already exists.");
    }
}
