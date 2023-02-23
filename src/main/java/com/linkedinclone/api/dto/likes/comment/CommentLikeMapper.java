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
@Mapper(componentModel = "spring", uses = CommentLikeMapper.class)
public interface CommentLikeMapper {

    ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "client.id", target = "userId")
    @Mapping(source = "comment.id", target = "commentId")
    CommentLikeDTO toCommentLikeDTO(CommentLike like);

    CommentLikeResponseDTO toCLikeResponseDTO(CommentLike cLike);

    @AfterMapping
    default void setClientSummaryDTO(@MappingTarget CommentLikeResponseDTO cLikeResponseDTO, CommentLike cLike) {
        cLikeResponseDTO.setClient(clientMapper.toClientSummaryDTO(cLike.getClient()));
    }

}
