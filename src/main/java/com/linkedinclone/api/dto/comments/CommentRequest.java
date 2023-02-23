package com.linkedinclone.api.dto.comments;

public record CommentRequest(
        String content,
        Long clientId,
        Long postId
) {
}