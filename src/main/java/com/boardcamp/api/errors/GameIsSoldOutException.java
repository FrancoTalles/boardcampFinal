package com.boardcamp.api.errors;

public class GameIsSoldOutException extends RuntimeException{
    public GameIsSoldOutException(String message) {
        super(message);
    }
}
