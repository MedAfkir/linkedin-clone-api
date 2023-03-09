package com.linkedinclone.api.dto.comments;

import com.linkedinclone.api.models.comments.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(target = "postId", source = "post.id")
    CommentDTO toCommentDTO(Comment comment);
    List<CommentDTO> toCommentDTO(List<Comment> comment);

    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "clientId", source = "client.id")
    CommentSummaryDTO toCommentSummaryDTO(Comment comment);
    List<CommentSummaryDTO> toCommentSummaryDTO(List<Comment> comment);

    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    Comment updateComment(CommentUpdateRequest request, Comment comment);

}
