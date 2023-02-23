package com.linkedinclone.api.dto.users;

public record UserLoginRequest(
        String username,
        String password
) {
}
