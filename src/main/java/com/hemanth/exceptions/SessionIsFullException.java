package com.hemanth.exceptions;

public class SessionIsFullException extends Exception {
    public SessionIsFullException(String message) {
        super(message);
    }
}
