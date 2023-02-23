package com.linkedinclone.api.dto.experiences;

import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.models.experiences.Experience;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExperienceWithClientDTO {
    private Long id;
    private String jobTitle;
    private String companyName;
    private String description;
    private ClientSummaryDTO client;
    private Date createdAt;
    private Date updatedAt;
}
