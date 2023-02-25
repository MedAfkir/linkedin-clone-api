package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.requests.Request;
import com.linkedinclone.api.models.requests.RequestState;
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
public class RequestNotification extends Notification<Request, RequestState> {

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request payload;

    private RequestState action;

}
