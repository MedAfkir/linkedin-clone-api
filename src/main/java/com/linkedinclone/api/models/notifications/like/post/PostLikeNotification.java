package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.likes.LikeAction;
import com.linkedinclone.api.models.likes.post.PostLike;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PostLikeNotification extends Notification<PostLike, LikeAction> {

    @ManyToOne
    @JoinColumn(name = "like_id")
    private PostLike payload;

    private LikeAction action;

}
