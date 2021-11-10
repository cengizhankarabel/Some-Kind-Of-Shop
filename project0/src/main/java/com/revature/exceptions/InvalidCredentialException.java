package com.revature.exceptions;

public class InvalidCredentialException extends RuntimeException{

    public InvalidCredentialException(String message) {
        super(message);
    }
}
