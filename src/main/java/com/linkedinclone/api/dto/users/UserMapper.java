package com.linkedinclone.api.dto.users;

import com.linkedinclone.api.models.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    User updateUser(UserUpdateRequest request, @MappingTarget User user);
}
