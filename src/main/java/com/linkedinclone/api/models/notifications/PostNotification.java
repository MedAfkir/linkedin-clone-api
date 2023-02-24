package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.posts.Post;
import com.linkedinclone.api.models.posts.PostAction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostNotification extends Notification<Post, PostAction> {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post payload;

    private PostAction action;

}
