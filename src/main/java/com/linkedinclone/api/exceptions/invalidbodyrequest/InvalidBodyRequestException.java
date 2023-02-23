package com.linkedinclone.api.exceptions.invalidbodyrequest;

public class InvalidBodyRequestException extends Exception {
    public InvalidBodyRequestException(String message) {
        super(message);
    }

    public InvalidBodyRequestException() {
        this("Invalid Body Request");
    }
}
