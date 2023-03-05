package com.linkedinclone.api.dto.users;

import com.linkedinclone.api.models.images.Image;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String image;

}
