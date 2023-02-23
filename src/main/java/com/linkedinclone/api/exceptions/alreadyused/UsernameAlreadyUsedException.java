package com.linkedinclone.api.exceptions.alreadyused;

public class UsernameAlreadyUsedException extends AlreadyUsedException {
    public UsernameAlreadyUsedException(String username) {
        super("Username " + username + " already used !");
    }

    public UsernameAlreadyUsedException() {
        super("Username already used !");
    }
}
