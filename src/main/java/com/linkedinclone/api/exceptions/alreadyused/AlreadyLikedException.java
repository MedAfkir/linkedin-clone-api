package com.linkedinclone.api.exceptions.alreadyused;

public class AlreadyLikedException extends AlreadyUsedException{
    public AlreadyLikedException() {
        this("Already liked");
    }

    public AlreadyLikedException(String message) {
        super(message);
    }
}
