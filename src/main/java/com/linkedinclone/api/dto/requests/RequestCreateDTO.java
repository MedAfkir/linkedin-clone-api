package com.linkedinclone.api.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestCreateDTO {
    private Long senderId;
    private Long receiverId;
}
