package com.linkedinclone.api.dto.comments;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CommentSummaryDTO {
    private Long id;
    private String content;
    private Long clientId;
    private Long postId;
    private Date createdAt;
    private Date updatedAt;
}
