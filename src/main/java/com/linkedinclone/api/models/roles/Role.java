package com.linkedinclone.api.models.roles;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    SUPER_ADMIN,
    OWNER;

    @Override
    public String getAuthority() {
        return this.toString();
    }

    public static Collection<Role> getRoles() {
        return EnumSet.allOf(Role.class);
    }

    public static Collection<Role> getAdminRoles() {
        return getRoles().stream().filter(role -> !role.isUser() && !role.isOwner()).toList();
    }

    public boolean isAdmin() {
        return equals(ADMIN);
    }

    public boolean isSuperAdmin() {
        return equals(SUPER_ADMIN);
    }

    public boolean isUser() {
        return equals(USER);
    }

    public boolean isOwner() {
        return equals(OWNER);
    }
}

