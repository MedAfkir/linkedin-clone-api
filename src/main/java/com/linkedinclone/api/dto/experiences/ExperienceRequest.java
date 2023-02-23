package com.linkedinclone.api.dto.experiences;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record ExperienceRequest(
        String jobTitle,
        String companyName,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date startDate,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date endDate,
        Long clientId
) {
}