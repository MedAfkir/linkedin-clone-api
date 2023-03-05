package com.linkedinclone.api.exceptions.notfound;

import com.linkedinclone.api.models.notifications.Notification;

public class NotificationNotFoundException extends NotFoundException {
    public NotificationNotFoundException(String message) {
        super(Notification.class, message);
    }

    public NotificationNotFoundException() {
        this("Notification Not Found");
    }
}
