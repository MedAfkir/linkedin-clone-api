package com.linkedinclone.api.dto.positions;

import com.linkedinclone.api.models.positions.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionRequest {
    private String title;
    private String description;

    public Position toPosition() {
        Position position = new Position();
        position.setTitle(title);
        position.setDescription(description);
        return position;
    }
}
