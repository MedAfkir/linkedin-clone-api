package com.linkedinclone.api.dto.skills;

import com.linkedinclone.api.models.skills.Skill;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
public class SkillDTO {
    private Long id;
    private String label;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public static SkillDTO fromSkill(Skill skill) {
        return new SkillDTO(
                skill.getId(),
                skill.getLabel(),
                skill.getDescription(),
                skill.getCreatedAt(),
                skill.getUpdatedAt());
    }
}
