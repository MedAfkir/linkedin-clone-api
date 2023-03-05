package com.linkedinclone.api.dto.notifications;

import com.linkedinclone.api.models.ActionType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseNotificationDTO<T, S extends ActionType> {

    private Long id;

    private T payload;

    private S action;

    private Date createdAt;

    private Date updatedAt;

    public static class TargetDTO {

        private Long userId;



    }

}
