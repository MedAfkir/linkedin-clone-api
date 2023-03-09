package com.linkedinclone.api.dto.positions;

import com.linkedinclone.api.models.positions.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PositionMapper {
    PositionDTO toPositionDTO(Position position);

    Position updatePosition(PositionRequest request, @MappingTarget Position position);

    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    Position createPosition(PositionRequest request);
}
