package com.linkedinclone.api.models.notifications.like.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeNotificationRepository extends JpaRepository<CommentLikeNotification, Long> {
}
