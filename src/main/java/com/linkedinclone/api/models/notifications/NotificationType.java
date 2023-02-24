package com.linkedinclone.api.models.notifications;

public interface NotificationType<T, S> {

    T getPayload();
    void setPayload(T payload);

    S getAction();
    void setAction(S action);

}
