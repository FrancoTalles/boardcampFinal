package com.boardcamp.api.errors;

public class RentalNotFoundException extends RuntimeException{
    public RentalNotFoundException(String message) {
        super(message);
    }
}
