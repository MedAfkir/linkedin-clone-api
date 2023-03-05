package com.linkedinclone.api.models.notifications.position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionNotificationRepository extends JpaRepository<PositionNotification, Long> {
}
