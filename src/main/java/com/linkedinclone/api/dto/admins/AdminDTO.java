package com.linkedinclone.api.dto.admins;

import com.linkedinclone.api.models.admins.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDTO {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phoneNumber;
    private Admin.Approval approval;
}