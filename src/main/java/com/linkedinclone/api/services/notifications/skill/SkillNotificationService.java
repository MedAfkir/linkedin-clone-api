package com.linkedinclone.api.services.notifications.skill;

import com.linkedinclone.api.models.notifications.Notification;
import com.linkedinclone.api.models.users.User;
import com.linkedinclone.api.services.notifications.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillNotificationService implements NotificationService {

    @Override
    public List<User> sendTo(Notification notification) {
        return null;
    }

}
