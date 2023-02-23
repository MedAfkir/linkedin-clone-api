package com.linkedinclone.api.dto.experiences;

import com.linkedinclone.api.models.experiences.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {
    @Mapping(target = "clientId", source = "client.id")
    ExperienceDTO toExperienceDTO(Experience experience);

    ExperienceWithClientDTO toExperienceWithClientDTO(Experience experience);
}