package com.linkedinclone.api.dto.posts;

import com.linkedinclone.api.dto.comments.CommentDTO;
import com.linkedinclone.api.dto.comments.CommentMapper;
import com.linkedinclone.api.models.comments.Comment;
import com.linkedinclone.api.models.posts.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface PostMapper {
    PostDTO toPostDTO(Post post);

    @Mapping(target = "clientId", source = "client.id")
    PostSummaryDTO toPostSummaryDTO(Post post);

    @Mapping(target = "likesCount", expression = "java(post.getLikes().size())")
    @Mapping(target = "commentsCount",  expression = "java(post.getComments().size())")
    @Mapping(target = "friendComment", ignore = true)
    @Mapping(target = "likeTypes", ignore = true)
    PostResponseDTO toPostResponseDTO(Post post);


}
