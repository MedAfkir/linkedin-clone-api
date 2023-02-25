package com.linkedinclone.api.dto.clients;

import com.linkedinclone.api.dto.users.UserRegistrationRequest;
import com.linkedinclone.api.dto.users.UserUpdateRequest;
import com.linkedinclone.api.models.clients.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "imageUrl", source = "image.url")
    ClientDTO toClientDTO(Client client);
    @Mapping(target = "imageUrl", source = "image.url")
    ClientSummaryDTO toClientSummaryDTO(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "postLikes", ignore = true)
    @Mapping(target = "commentLikes", ignore = true)
    @Mapping(target = "privateAccount", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    Client updateClient(UserUpdateRequest request, @MappingTarget Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "postLikes", ignore = true)
    @Mapping(target = "commentLikes", ignore = true)
    @Mapping(target = "privateAccount", ignore = true)
    @Mapping(target = "image", ignore = true)
    Client createClient(UserRegistrationRequest request);
}
