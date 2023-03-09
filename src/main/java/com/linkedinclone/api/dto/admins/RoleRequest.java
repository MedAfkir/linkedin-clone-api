package com.linkedinclone.api.dto.admins;

import com.linkedinclone.api.models.roles.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    @NotNull
    private Role role;
}
