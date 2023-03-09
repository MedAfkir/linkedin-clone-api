package com.linkedinclone.api.dto.posts;

import com.linkedinclone.api.dto.comments.CommentMapper;
import com.linkedinclone.api.dto.images.ImageMapper;
import com.linkedinclone.api.models.posts.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, ImageMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    PostDTO toPostDTO(Post post);
    List<PostDTO> toPostDTO(List<Post> posts);

    @Mapping(target = "clientId", source = "client.id")
    PostSummaryDTO toPostSummaryDTO(Post post);
    @Mapping(target = "clientId", source = "client.id")
    List<PostSummaryDTO> toPostSummaryDTO(List<Post> post);

    @Mapping(target = "likesCount", expression = "java(post.getLikes().size())")
    @Mapping(target = "commentsCount",  expression = "java(post.getComments().size())")
    PostResponseDTO toPostResponseDTO(Post post);
    @Mapping(target = "likesCount", expression = "java(post.getLikes().size())")
    @Mapping(target = "commentsCount",  expression = "java(post.getComments().size())")
    List<PostResponseDTO> toPostResponseDTO(List<Post> post);

    @Mapping(target = "likesCount", expression = "java(post.getLikes().size())")
    @Mapping(target = "commentsCount",  expression = "java(post.getComments().size())")
    PostSimpleResponseDTO toPostSimpleResponseDTO(Post post);

    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    Post updatePost(PostRequest request, @MappingTarget Post post);

    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    Post createPost(PostRequest request);
}
