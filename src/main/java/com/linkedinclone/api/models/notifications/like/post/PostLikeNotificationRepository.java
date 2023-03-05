package com.linkedinclone.api.models.notifications.like.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeNotificationRepository extends JpaRepository<PostLikeNotification, Long> {
}
