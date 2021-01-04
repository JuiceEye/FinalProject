package com.github.JuiceEye.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String errorMessage) {
        super(errorMessage);
    }
}
