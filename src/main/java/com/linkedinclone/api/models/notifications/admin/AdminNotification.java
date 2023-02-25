package com.linkedinclone.api.models.notifications.admin;

import com.linkedinclone.api.models.admins.Admin;
import com.linkedinclone.api.models.admins.AdminAction;
import com.linkedinclone.api.models.notifications.Notification;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdminNotification extends Notification<Admin, AdminAction> {

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin payload;

    private AdminAction action;

}
