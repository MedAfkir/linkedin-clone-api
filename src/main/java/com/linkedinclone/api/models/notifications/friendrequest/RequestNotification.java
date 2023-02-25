package com.linkedinclone.api.models.notifications.friendrequest;

import com.linkedinclone.api.models.notifications.Notification;
import com.linkedinclone.api.models.requests.Request;
import com.linkedinclone.api.models.requests.RequestAction;
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
public class RequestNotification extends Notification<Request, RequestAction> {

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request payload;

    private RequestAction action;

}
