package com.linkedinclone.api.models.notifications.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostNotificationRepository extends JpaRepository<PostNotification, Long> {
}
