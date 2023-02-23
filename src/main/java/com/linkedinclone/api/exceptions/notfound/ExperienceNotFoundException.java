package com.linkedinclone.api.exceptions.notfound;

import com.linkedinclone.api.models.experiences.Experience;

public class ExperienceNotFoundException extends NotFoundException {
    public ExperienceNotFoundException(String message) {
        super(Experience.class, message);
    }

    public ExperienceNotFoundException() {
        this("Experience Not Found");
    }
}
