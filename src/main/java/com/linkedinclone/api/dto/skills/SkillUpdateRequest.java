package com.linkedinclone.api.dto.skills;

import com.linkedinclone.api.models.skills.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkillUpdateRequest {
    private String label;
    private String description;
}
