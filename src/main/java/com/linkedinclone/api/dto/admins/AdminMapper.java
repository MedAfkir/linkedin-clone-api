package com.linkedinclone.api.dto.admins;

import com.linkedinclone.api.dto.users.UserRegistrationRequest;
import com.linkedinclone.api.dto.users.UserUpdateRequest;
import com.linkedinclone.api.models.admins.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminDTO toAdminDTO(Admin admin);

    List<AdminDTO> toAdminsDTOs(List<Admin> admins);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "approval", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    Admin updateAdmin(UserUpdateRequest request, @MappingTarget Admin admin);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "approval", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Admin createAdmin(UserRegistrationRequest request);
}
