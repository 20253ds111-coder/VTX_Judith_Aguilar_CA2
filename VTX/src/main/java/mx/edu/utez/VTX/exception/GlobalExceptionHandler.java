package mx.edu.utez.VTX.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<Map<String, String>> handleNotValid(NotValidException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(NotCorrectException.class)
    public ResponseEntity<Map<String, String>> handleNotCorrect(NotCorrectException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(400).body(response);
    }
}
