package com.linkedinclone.api.services.notifications;

import com.linkedinclone.api.models.notifications.Notification;
import com.linkedinclone.api.models.users.User;

import java.util.List;

public interface NotificationStrategy<T extends Notification<?, ?>> {
    List<User> sendTo(T notification);
}
