package com.linkedinclone.api.exceptions.notfound;

import com.linkedinclone.api.models.admins.Admin;

public class AdminNotFoundException extends NotFoundException {
    public AdminNotFoundException(String message) {
        super(Admin.class, message);
    }

    public AdminNotFoundException() {
        this("Admin Not Found");
    }
}
