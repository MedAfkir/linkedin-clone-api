package com.linkedinclone.api.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PostSummaryDTO {
    private Long id;
    private String content;
    private Long clientId;
    private Date createdAt;
    private Date updatedAt;
}
