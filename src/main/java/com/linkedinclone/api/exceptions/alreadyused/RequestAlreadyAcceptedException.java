package com.linkedinclone.api.exceptions.alreadyused;

public class RequestAlreadyAcceptedException extends AlreadyUsedException{

    public RequestAlreadyAcceptedException() {
        this("Request was already accepted");
    }

    public RequestAlreadyAcceptedException(String message) {
        super(message);
    }
}
