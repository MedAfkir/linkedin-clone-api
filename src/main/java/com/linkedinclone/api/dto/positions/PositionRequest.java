package com.linkedinclone.api.dto.positions;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
