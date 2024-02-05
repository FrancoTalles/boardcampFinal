package com.boardcamp.api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
    
    @ExceptionHandler({ GameAlreadyExistsException.class })
    public ResponseEntity<String> handleGameAlreadyExists(GameAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({ CustomerNotFoundException.class })
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ CustomerAlreadyExistsException.class })
    public ResponseEntity<String> handleCustomerAlreadyExists(CustomerAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({ GameNotFoundException.class })
    public ResponseEntity<String> handleGameNotFound(GameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ GameIsSoldOutException.class })
    public ResponseEntity<String> handleGameSoldOut(GameIsSoldOutException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ExceptionHandler({ GameAlreadyRentedException.class })
    public ResponseEntity<String> handleGameAlreadyRented(GameAlreadyRentedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({ RentalNotFoundException.class })
    public ResponseEntity<String> handleRentalNotFound(RentalNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ RentalAlreadyFinishException.class })
    public ResponseEntity<String> handleRentalAlreadyFinish(RentalAlreadyFinishException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
