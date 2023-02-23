package com.linkedinclone.api.dto.likes.comment;

import com.linkedinclone.api.dto.clients.ClientMapper;
import com.linkedinclone.api.models.likes.comment.CommentLike;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author adil
 */
@Mapper(componentModel = "spring", uses = ClientMapper.class)
public interface CommentLikeMapper {


    @Mapping(source = "client.id", target = "userId")
    @Mapping(source = "comment.id", target = "commentId")
    CommentLikeDTO toCommentLikeDTO(CommentLike like);

    @Mapping(source = "client", target = "client")
    CommentLikeResponseDTO toCLikeResponseDTO(CommentLike cLike);



}
