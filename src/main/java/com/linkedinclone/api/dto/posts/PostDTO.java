package com.linkedinclone.api.dto.posts;

import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.dto.comments.CommentDTO;
import com.linkedinclone.api.models.posts.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String content;
    private ClientSummaryDTO client;
    private List<CommentDTO> comments;
    private Date createdAt;
    private Date updatedAt;
}
