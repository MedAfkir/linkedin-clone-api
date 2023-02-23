package com.linkedinclone.api.exceptions.notfound;

import com.linkedinclone.api.models.clients.Client;

public class ClientNotFoundException extends NotFoundException {
    public ClientNotFoundException(String message) {
        super(Client.class, message);
    }

    public ClientNotFoundException() {
        this("Client Not Found");
    }
}
