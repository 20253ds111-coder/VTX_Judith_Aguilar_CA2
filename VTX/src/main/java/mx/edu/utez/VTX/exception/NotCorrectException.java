package mx.edu.utez.VTX.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotCorrectException extends RuntimeException {
    public NotCorrectException(String message) {
        super(message);
    }
}
