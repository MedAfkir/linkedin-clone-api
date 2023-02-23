package com.linkedinclone.api.exceptions.notfound;

import com.linkedinclone.api.models.skills.Skill;

public class SkillNotFoundException extends NotFoundException {
    public SkillNotFoundException(String message) {
        super(Skill.class, message);
    }

    public SkillNotFoundException() {
        this("Skill Not Found");
    }
}
