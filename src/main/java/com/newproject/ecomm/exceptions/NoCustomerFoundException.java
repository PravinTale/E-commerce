package com.newproject.ecomm.exceptions;

public class NoCustomerFoundException extends RuntimeException {
    public NoCustomerFoundException(String message) {
        super(message);
    }
}
