package com.linkedinclone.api.dto.experiences;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExperienceDTO {
    private Long id;
    private String jobTitle;
    private String companyName;
    private String description;
    private Long clientId;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private Date updatedAt;
}
