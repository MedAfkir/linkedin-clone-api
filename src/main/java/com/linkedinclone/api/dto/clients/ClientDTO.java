package com.linkedinclone.api.dto.clients;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phoneNumber;
    private String imageUrl;
}
