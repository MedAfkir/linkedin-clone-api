package com.linkedinclone.api.dto.likes.post;

import com.linkedinclone.api.models.likes.LikeType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author adil
 */
@Data
@AllArgsConstructor
public class PostLikeUpdateDTO {
    private LikeType type;
}
