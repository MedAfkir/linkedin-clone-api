package com.linkedinclone.api.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenRequest {
    private String accessToken;
}
