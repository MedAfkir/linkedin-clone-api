package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.ActionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public abstract class Notification<T, S extends ActionType> implements NotificationType<T, S> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    private Date updatedAt;

    @OneToMany(mappedBy = "notification")
    private List<UserNotificationState> userNotificationStates;

}
