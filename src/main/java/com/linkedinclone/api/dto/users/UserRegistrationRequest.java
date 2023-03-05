package com.linkedinclone.api.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserRegistrationRequest {

    @NotBlank
    @Size(min = 2, max = 100, message = "Firstname size should be between {min} & {max}")
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 100, message = "Lastname size should be between {min} & {max}")
    private String lastname;

    @NotBlank
    @Pattern(regexp = "/^[a-zA-Z0-9]+$/", message = "Username invalid")
    @Size(min = 5, max = 100, message = "Username size should be between {min} & {max}")
    private String username;

    @Email(message = "Email invalid")
    private String email;

    @NotBlank
    @Size(min = 2, max = 100, message = "Firstname size should be between {min} & {max}")
    private String address;

    @NotBlank
    @Size(min = 8, max = 100, message = "Firstname size should be between {min} & {max}")
    private String password;

    @NotBlank
    @Pattern(regexp = "/^(\\d{10}|\\+\\d{1,3}\\d{9})$/\n", message = "Phone number invalid")
    private String phoneNumber;

}
