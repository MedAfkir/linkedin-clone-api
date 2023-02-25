package com.linkedinclone.api.dto.posts;

import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.dto.comments.CommentDTO;
import com.linkedinclone.api.models.likes.LikeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostSimpleResponseDTO {
    private Long id;
    private String content;
    private ClientSummaryDTO client;
    private int likesCount;
    private int commentsCount;
    private List<String> images;
    private List<LikeType> likeTypes;
    private Date createdAt;
    private Date updatedAt;
}
