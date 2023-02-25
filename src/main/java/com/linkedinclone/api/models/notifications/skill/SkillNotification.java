package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.skills.Skill;
import com.linkedinclone.api.models.skills.SkillAction;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SkillNotification extends Notification<Skill, SkillAction> {

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill payload;

    private SkillAction action;

}
