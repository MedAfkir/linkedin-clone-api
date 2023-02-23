package com.linkedinclone.api.dto.requests;

import com.linkedinclone.api.models.requests.RequestState;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestUpdateDTO {
    private Long requestId;
    private RequestState requestState;
}
