package com.linkedinclone.api.models.admins;

import com.linkedinclone.api.models.ActionType;

public enum AdminAction implements ActionType {
    CREATE,
    UPDATE,
    DELETE,

    APPROVE,
    DISAPPROVE,

    SET_ROLE
}
