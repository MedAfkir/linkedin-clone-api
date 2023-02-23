package com.linkedinclone.api.exceptions.alreadyused;

public class RequestAlreadySentException extends AlreadyUsedException{
    public RequestAlreadySentException() {
        this("Request was already sent");
    }

    public RequestAlreadySentException(String message) {
        super(message);
    }
}
