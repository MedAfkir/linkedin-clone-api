package com.linkedinclone.api.models.admins;

import com.linkedinclone.api.models.roles.Role;
import com.linkedinclone.api.models.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User {
    @Column(name = "approved", nullable = false)
    @Enumerated
    private Approval approval;

    @Enumerated
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    public enum Approval {
        PENDING,
        APPROVED,
        NOT_APPROVED;

        private Approval() {}

        public boolean isApproved() {
            return this.equals(APPROVED);
        }

        public boolean isNotApproved() {
            return this.equals(NOT_APPROVED);
        }

        public boolean isPending() {
            return this.equals(PENDING);
        }
    }
}