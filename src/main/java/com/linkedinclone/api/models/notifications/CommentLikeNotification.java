package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.likes.LikeAction;
import com.linkedinclone.api.models.likes.comment.CommentLike;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentLikeNotification extends Notification<CommentLike, LikeAction> {

    @ManyToOne
    @JoinColumn(name = "like_id")
    private CommentLike payload;

    private LikeAction action;

}
