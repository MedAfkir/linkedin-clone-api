package com.linkedinclone.api.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestCreateDTO {
    @NotNull
    private Long senderId;
    @NotNull
    private Long receiverId;
}
