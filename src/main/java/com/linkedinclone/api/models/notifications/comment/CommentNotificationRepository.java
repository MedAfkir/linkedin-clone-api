package com.linkedinclone.api.models.notifications.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentNotificationRepository extends JpaRepository<CommentNotification, Long> {
}
