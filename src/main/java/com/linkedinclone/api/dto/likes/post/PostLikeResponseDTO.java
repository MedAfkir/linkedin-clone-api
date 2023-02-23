package com.linkedinclone.api.dto.likes.post;

import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.models.likes.LikeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostLikeResponseDTO {
    private Long id;
    private LikeType type;
    private ClientSummaryDTO client;

}
