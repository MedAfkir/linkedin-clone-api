package com.linkedinclone.api.dto.skills;

import com.linkedinclone.api.models.skills.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillDTO toSkillDTO(Skill skill);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    Skill updateSkill(SkillUpdateRequest request, @MappingTarget Skill skill);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Skill createSkill(SkillAddRequest request);
}
