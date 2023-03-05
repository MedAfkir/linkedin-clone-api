package com.linkedinclone.api.services.notifications.admin;

import com.linkedinclone.api.models.notifications.admin.AdminNotification;
import com.linkedinclone.api.models.users.User;
import com.linkedinclone.api.services.notifications.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminNotificationService implements NotificationService<AdminNotification> {
    public List<AdminNotification> getAllNotifications() {
        return null;
    }

    @Override
    public List<? extends User> sendTo(AdminNotification notification) {
        return null;
    }
}
