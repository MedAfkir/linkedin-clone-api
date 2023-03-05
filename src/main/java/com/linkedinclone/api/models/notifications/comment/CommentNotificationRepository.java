package com.linkedinclone.api.models.notifications.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentNotificationRepository extends JpaRepository<CommentNotification, Long> {
}
