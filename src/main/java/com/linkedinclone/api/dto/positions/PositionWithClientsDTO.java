package com.linkedinclone.api.dto.positions;

import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PositionWithClientsDTO {
    private Long id;
    private String title;
    private String description;
    private List<ClientSummaryDTO> clients;
    private Date createdAt;
    private Date updatedAt;
}
