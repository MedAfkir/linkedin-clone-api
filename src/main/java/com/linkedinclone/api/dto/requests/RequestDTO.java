package com.linkedinclone.api.dto.requests;

import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.models.requests.RequestState;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RequestDTO {
    private Long id;
    private ClientSummaryDTO sender;
    private ClientSummaryDTO receiver;
    private RequestState state;
    private Date sentAt;
    private Date acceptedAt;
}
