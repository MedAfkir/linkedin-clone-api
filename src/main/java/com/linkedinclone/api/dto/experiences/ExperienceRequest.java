package com.linkedinclone.api.dto.experiences;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceRequest {
        @NotBlank
        private String jobTitle;
        @NotBlank
        private String companyName;
        @NotBlank
        private String description;
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date startDate;
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date endDate;
        @NotNull
        private Long clientId;
}