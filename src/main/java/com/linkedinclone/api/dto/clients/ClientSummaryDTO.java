package com.linkedinclone.api.dto.clients;

import com.linkedinclone.api.models.clients.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientSummaryDTO {
    private Long id;
    private String firstname;
    private String lastname;
}
