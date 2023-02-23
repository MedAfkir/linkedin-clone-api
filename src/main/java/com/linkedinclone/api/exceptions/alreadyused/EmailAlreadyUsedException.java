package com.linkedinclone.api.exceptions.alreadyused;

public class EmailAlreadyUsedException extends AlreadyUsedException {
    public EmailAlreadyUsedException(String email) {
        super("Email " + email + " already used !");
    }

    public EmailAlreadyUsedException() {
        super("Email already used !");
    }
}
