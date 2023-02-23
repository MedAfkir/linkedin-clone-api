package com.linkedinclone.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String password;
    private String phoneNumber;
}
