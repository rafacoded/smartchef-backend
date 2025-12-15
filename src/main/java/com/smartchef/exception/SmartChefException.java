package com.smartchef.exception;

public abstract class SmartChefException extends RuntimeException {
    public SmartChefException(String message) {
        super(message);
    }
}
