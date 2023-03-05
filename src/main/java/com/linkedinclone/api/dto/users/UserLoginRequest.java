package com.linkedinclone.api.dto.users;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
}
