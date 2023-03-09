package com.linkedinclone.api.dto.clients;

import com.linkedinclone.api.dto.users.UserRegistrationRequest;
import com.linkedinclone.api.dto.users.UserUpdateRequest;
import com.linkedinclone.api.models.clients.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    @Mapping(target = "imageUrl", source = "image.url")
    ClientDTO toClientDTO(Client client);
    @Mapping(target = "imageUrl", source = "image.url")
    ClientSummaryDTO toClientSummaryDTO(Client client);

    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    Client updateClient(UserUpdateRequest request, @MappingTarget Client client);

    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    Client createClient(UserRegistrationRequest request);
}
