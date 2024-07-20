package GeminiConsult.Controllers;

import GeminiConsult.Exceptions.ErrorResponse;
import GeminiConsult.Exceptions.GenericExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(GenericExceptions.UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(GenericExceptions.UserNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenericExceptions.InvalidPasswordException.class)
    public ResponseEntity<?> handleInvalidPasswordException(GenericExceptions.InvalidPasswordException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    // Other exception handlers
}
