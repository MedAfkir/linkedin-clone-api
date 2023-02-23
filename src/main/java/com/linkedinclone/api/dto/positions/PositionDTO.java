package com.linkedinclone.api.dto.positions;

import com.linkedinclone.api.models.positions.Position;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
public class PositionDTO {
    private Long id;
    private String title;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
