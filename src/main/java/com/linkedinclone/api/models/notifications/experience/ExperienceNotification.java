package com.linkedinclone.api.models.notifications.experience;

import com.linkedinclone.api.models.experiences.Experience;
import com.linkedinclone.api.models.experiences.ExperienceAction;
import com.linkedinclone.api.models.notifications.Notification;
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
public class ExperienceNotification extends Notification<Experience, ExperienceAction> {

    @ManyToOne
    @JoinColumn(name = "experience_id")
    private Experience payload;

    private ExperienceAction action;

}
