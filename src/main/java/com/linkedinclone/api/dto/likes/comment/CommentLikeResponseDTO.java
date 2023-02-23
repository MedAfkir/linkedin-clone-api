package com.linkedinclone.api.dto.likes.comment;

import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.models.likes.LikeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author adil
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentLikeResponseDTO {
    private Long id;
    private LikeType type;
    private ClientSummaryDTO client;
}

