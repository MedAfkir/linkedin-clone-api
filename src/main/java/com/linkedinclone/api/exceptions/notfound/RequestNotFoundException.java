package com.linkedinclone.api.exceptions.notfound;

public class RequestNotFoundException extends NotFoundException{
    public RequestNotFoundException(String message) {
        super(message);
    }

    public RequestNotFoundException() {
        this("Request not found");
    }
}
