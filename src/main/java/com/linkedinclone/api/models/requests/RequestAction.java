package com.linkedinclone.api.models.requests;

import com.linkedinclone.api.models.ActionType;

public enum RequestAction implements ActionType {
    CREATE,
    DELETE,
    ACCEPT,
    REFUSE
}
