package com.newproject.ecomm.exceptions;

public class DuplicateMobileNumberException extends RuntimeException{
    public DuplicateMobileNumberException(String message) {
        super(message);
    }
}
