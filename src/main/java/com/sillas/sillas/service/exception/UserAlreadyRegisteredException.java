package com.sillas.sillas.service.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String username) {
        super("User: " + username + " is already registered");
    }
}
