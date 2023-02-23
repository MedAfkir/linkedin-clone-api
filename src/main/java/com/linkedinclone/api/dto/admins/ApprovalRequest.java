package com.linkedinclone.api.dto.admins;

import com.linkedinclone.api.models.admins.Admin;

public record ApprovalRequest (
        Admin.Approval approval
) {
}
