package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.positions.Position;
import com.linkedinclone.api.models.positions.PositionAction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionNotification extends Notification<Position, PositionAction> {

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Position payload;

    private PositionAction action;

}
