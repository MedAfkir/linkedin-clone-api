package com.linkedinclone.api.models.notifications.friendrequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestNotificationRepository extends JpaRepository<RequestNotification, Long> {
}
