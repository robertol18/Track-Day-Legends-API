package com.example.trackdaylegends.domain.exception;

public class BadRequestException extends DomainException {
    public BadRequestException(String message) {
        super(message);
    }
}
