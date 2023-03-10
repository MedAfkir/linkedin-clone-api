package com.linkedinclone.api.models.notifications.like.comment;

import com.linkedinclone.api.models.likes.LikeAction;
import com.linkedinclone.api.models.likes.comment.CommentLike;
import com.linkedinclone.api.models.notifications.Notification;
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
public class CommentLikeNotification extends Notification<CommentLike, LikeAction> {

    @ManyToOne
    @JoinColumn(name = "like_id")
    private CommentLike payload;

    private LikeAction action;

}
