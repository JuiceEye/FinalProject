package com.github.JuiceEye.exceptions;

public class InvalidLoginCredentialsException extends Exception {
    public InvalidLoginCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}

