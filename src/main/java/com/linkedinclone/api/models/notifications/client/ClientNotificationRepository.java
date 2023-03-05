package com.linkedinclone.api.models.notifications.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientNotificationRepository extends JpaRepository<ClientNotification, Long> {
}
