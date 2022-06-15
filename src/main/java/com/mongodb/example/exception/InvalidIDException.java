package com.mongodb.example.exception;

public class InvalidIDException extends RuntimeException {
    public InvalidIDException() {
        super("Exception happened");

    }

    public InvalidIDException(String message) {
        super(message);
    }
}
