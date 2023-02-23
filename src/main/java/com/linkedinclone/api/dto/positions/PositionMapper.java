package com.linkedinclone.api.dto.positions;

import com.linkedinclone.api.models.positions.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionDTO toPositionDTO(Position position);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Position updatePosition(PositionRequest request, @MappingTarget Position position);
}
