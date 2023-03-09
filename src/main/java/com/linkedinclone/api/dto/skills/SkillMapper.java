package com.linkedinclone.api.dto.skills;

import com.linkedinclone.api.models.skills.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SkillMapper {
    SkillDTO toSkillDTO(Skill skill);

    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    Skill updateSkill(SkillUpdateRequest request, @MappingTarget Skill skill);

    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    Skill createSkill(SkillAddRequest request);
}
