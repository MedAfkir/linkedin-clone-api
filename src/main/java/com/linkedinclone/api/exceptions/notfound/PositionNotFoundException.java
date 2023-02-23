package com.linkedinclone.api.exceptions.notfound;

import com.linkedinclone.api.models.positions.Position;

public class PositionNotFoundException extends NotFoundException {
    public PositionNotFoundException(String message) {
        super(Position.class, message);
    }

    public PositionNotFoundException() {
        this("Position Not Found");
    }
}
