package com.linkedinclone.api.services.notifications.admin;

import com.linkedinclone.api.models.notifications.admin.AdminNotification;
import com.linkedinclone.api.models.users.User;
import com.linkedinclone.api.services.notifications.NotificationStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminNotificationService implements NotificationStrategy<AdminNotification> {

    public List<AdminNotification> getAllNotifications() {
        return null;
    }

    @Override
    public List<User> sendTo(AdminNotification notification) {
        return null;
    }

}
