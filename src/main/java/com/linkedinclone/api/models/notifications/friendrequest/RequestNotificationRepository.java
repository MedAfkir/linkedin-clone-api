package com.linkedinclone.api.models.notifications.friendrequest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestNotificationRepository extends JpaRepository<RequestNotification, Long> {
}
