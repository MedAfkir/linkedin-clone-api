package com.linkedinclone.api.exceptions.notfound;

public class LikeNotFoundException extends NotFoundException{
    public LikeNotFoundException() {
        this("Like not found");
    }

    public LikeNotFoundException(String message) {
        super(message);
    }
}
