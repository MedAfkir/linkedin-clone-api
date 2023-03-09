package com.linkedinclone.api.dto.skills;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkillUpdateRequest {
    @NotBlank
    private String label;
    @NotBlank
    private String description;
}
