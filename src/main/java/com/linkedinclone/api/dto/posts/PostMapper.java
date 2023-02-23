package com.linkedinclone.api.dto.posts;

import com.linkedinclone.api.dto.comments.CommentMapper;
import com.linkedinclone.api.models.posts.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface PostMapper {
    PostDTO toPostDTO(Post post);

    @Mapping(target = "clientId", source = "client.id")
    PostSummaryDTO toPostSummaryDTO(Post post);
}
