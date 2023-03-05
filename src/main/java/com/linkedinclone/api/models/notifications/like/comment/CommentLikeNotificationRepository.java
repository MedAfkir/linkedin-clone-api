package com.linkedinclone.api.models.notifications.like.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeNotificationRepository extends JpaRepository<CommentLikeNotification, Long> {
}
