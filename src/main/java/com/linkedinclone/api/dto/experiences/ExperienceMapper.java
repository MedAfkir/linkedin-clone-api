package com.linkedinclone.api.dto.experiences;

import com.linkedinclone.api.models.experiences.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExperienceMapper {
    @Mapping(target = "clientId", source = "client.id")
    ExperienceDTO toExperienceDTO(Experience experience);
    List<ExperienceDTO> toExperienceDTO(List<Experience> experience);

    ExperienceWithClientDTO toExperienceWithClientDTO(Experience experience);

    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    Experience updateExperience(ExperienceRequest request, @MappingTarget Experience experience);

    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    Experience createExperience(ExperienceRequest request);
}