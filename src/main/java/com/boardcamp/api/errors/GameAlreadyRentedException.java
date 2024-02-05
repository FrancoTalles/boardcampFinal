package com.boardcamp.api.errors;

public class GameAlreadyRentedException extends RuntimeException{
    public GameAlreadyRentedException(String message){
        super(message);
    }
}
