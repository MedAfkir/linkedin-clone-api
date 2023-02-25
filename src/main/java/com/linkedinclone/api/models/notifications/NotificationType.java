package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.ActionType;

public interface NotificationType<T, S extends ActionType> {

    T getPayload();
    void setPayload(T payload);

    S getAction();
    void setAction(S action);

}
