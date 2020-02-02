package tim10.project.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CoverLetterAlreadyExists extends RuntimeException {

    public CoverLetterAlreadyExists(String message) {super(message);}

    public CoverLetterAlreadyExists() {
        super("Cover letter with that name already exists.");
    }
}
