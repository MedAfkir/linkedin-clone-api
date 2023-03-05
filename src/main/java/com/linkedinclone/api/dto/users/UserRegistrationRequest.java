package com.linkedinclone.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserRegistrationRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String address;
    private String password;
    private String phoneNumber;
}
