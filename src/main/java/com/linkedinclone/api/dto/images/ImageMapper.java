package com.linkedinclone.api.dto.images;

import com.linkedinclone.api.models.images.Image;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {

    default String toPath(Image image) {
        return image.getUrl();
    }

}
