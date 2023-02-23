package com.linkedinclone.api.dto.comments;

import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.models.comments.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private ClientSummaryDTO client;
    private Long postId;
    private Date createdAt;
    private Date updatedAt;
}
