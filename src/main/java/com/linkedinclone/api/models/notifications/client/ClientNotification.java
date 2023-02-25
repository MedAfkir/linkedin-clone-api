package com.linkedinclone.api.models.notifications.client;

import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.clients.ClientAction;
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
public class ClientNotification extends Notification<Client, ClientAction> {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client payload;

    private ClientAction action;

}
