package com.linkedinclone.api.models.clients;

import com.linkedinclone.api.models.ActionType;

public enum ClientAction implements ActionType {
    CREATE,
    UPDATE,
    DELETE,

    ADD_SKILL,
    REMOVE_SKILL,

    DEFINE_POSITION,
    REMOVE_POSITION,
}
