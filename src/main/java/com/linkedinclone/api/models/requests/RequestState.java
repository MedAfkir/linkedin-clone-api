package com.linkedinclone.api.models.requests;

public enum RequestState {
    PENDING,
    REFUSED,
    ACCEPTED;

    public boolean isPending() {
        return this.equals(PENDING);
    }

    public boolean isRefused() {
        return this.equals(REFUSED);
    }

    public boolean isAccepted() {
        return this.equals(ACCEPTED);
    }

}
