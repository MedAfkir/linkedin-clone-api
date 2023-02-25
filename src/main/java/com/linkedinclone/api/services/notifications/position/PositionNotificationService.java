package com.linkedinclone.api.services.notifications;

import com.linkedinclone.api.models.notifications.Notification;
import com.linkedinclone.api.models.users.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionNotificationService implements NotificationStrategy {

    @Override
    public List<User> sendTo(Notification notification) {
        return null;
    }

}
