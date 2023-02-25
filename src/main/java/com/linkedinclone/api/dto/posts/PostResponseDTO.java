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

/**
 * return a custom post response contains extra informations
 * such as likes and comments count, and if any of user's friends
 * comment on this post it should return that comment,
 * and a sorted table of likes type
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostResponseDTO {
    private Long id;
    private String content;
    private ClientSummaryDTO client;
    private CommentDTO friendComment; // if any of friends comment on this post
    private int likesCount;
    private int commentsCount;
    private List<LikeType> likeTypes;
    private List<String> images;
    private Date createdAt;
    private Date updatedAt;
}
