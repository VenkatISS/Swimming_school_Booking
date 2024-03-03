package com.hemanth.exceptions;

public class SessionAlreadyBookedException extends Exception {
    public SessionAlreadyBookedException(String message) {
        super(message);
    }
}
