package com.linkedinclone.api.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Data;

public record PostRequest(
        String content,
        Long clientId
) {
}