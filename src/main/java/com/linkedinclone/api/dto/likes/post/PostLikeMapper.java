package com.linkedinclone.api.dto.likes.post;

import com.linkedinclone.api.models.likes.post.PostLike;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author adil
 */
@Mapper(componentModel = "spring")
public interface PostLikeMapper {

    @Mapping(source = "client.id", target = "userId")
    @Mapping(source = "post.id", target = "postId")
    PostLikeDTO toPostLikeDTO(PostLike like);

}
