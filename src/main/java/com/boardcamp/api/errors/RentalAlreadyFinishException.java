package com.boardcamp.api.errors;

public class RentalAlreadyFinishException extends RuntimeException{
    public RentalAlreadyFinishException(String message) {
        super(message);
    }
}
