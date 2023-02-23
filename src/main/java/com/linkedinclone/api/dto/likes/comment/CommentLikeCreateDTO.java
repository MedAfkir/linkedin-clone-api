package com.linkedinclone.api.dto.likes.comment;

import com.linkedinclone.api.models.likes.LikeType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author adil
 */
@Data
@AllArgsConstructor
public class CommentLikeCreateDTO {
    private LikeType type;
    private Long commentId;
    private Long userId;
}
