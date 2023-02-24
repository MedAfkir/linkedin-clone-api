package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.experiences.Experience;
import com.linkedinclone.api.models.experiences.ExperienceAction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceNotification extends Notification<Experience, ExperienceAction> {

    @ManyToOne
    @JoinColumn(name = "experience_id")
    private Experience payload;

    private ExperienceAction action;

}
