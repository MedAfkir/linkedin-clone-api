package com.linkedinclone.api.models.notifications.like.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeNotificationRepository extends JpaRepository<PostLikeNotification, Long> {
}
