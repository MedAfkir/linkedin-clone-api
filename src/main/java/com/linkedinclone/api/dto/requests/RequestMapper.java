package com.linkedinclone.api.dto.requests;

import com.linkedinclone.api.models.requests.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mapping(source = "sender.id", target = "sender.id")
    @Mapping(source = "sender.firstname", target = "sender.firstname")
    @Mapping(source = "sender.lastname", target = "sender.lastname")
    @Mapping(source = "receiver.id", target = "receiver.id")
    @Mapping(source = "receiver.firstname", target = "receiver.firstname")
    @Mapping(source = "receiver.lastname", target = "receiver.lastname")
    RequestDTO toRequestDTO(Request request);
}
