package com.linkedinclone.api.exceptions.alreadyused;

public class AlreadyUsedException extends Exception {
    public AlreadyUsedException() {
        super();
    }

    public AlreadyUsedException(String message) {
        super(message);
    }
}
