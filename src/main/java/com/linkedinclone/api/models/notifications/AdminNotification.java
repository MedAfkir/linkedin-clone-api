package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.admins.Admin;
import com.linkedinclone.api.models.admins.AdminAction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminNotification extends Notification<Admin, AdminAction> {

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin payload;

    private AdminAction action;

}
