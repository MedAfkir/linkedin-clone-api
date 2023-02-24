package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.clients.ClientAction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientNotification extends Notification<Client, ClientAction> {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client payload;

    private ClientAction action;

}
