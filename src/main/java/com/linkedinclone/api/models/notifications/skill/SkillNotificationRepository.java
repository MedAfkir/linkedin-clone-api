package com.linkedinclone.api.models.notifications.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillNotificationRepository extends JpaRepository<SkillNotification, Long> {
}
