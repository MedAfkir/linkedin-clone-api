package com.linkedinclone.api.dto.comments;

import com.linkedinclone.api.models.comments.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "postId", source = "post.id")
    CommentDTO toCommentDTO(Comment comment);

    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "clientId", source = "client.id")
    CommentSummaryDTO toCommentSummaryDTO(Comment comment);

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    Comment updateComment(CommentUpdateRequest request, Comment comment);
}
