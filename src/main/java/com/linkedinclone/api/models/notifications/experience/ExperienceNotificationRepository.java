package com.linkedinclone.api.models.notifications.experience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceNotificationRepository extends JpaRepository<ExperienceNotification, Long> {
}
