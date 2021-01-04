package com.github.JuiceEye.exceptions;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
