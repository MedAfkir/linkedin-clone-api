package com.linkedinclone.api.models.notifications;

import com.linkedinclone.api.models.comments.Comment;
import com.linkedinclone.api.models.comments.CommentAction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentNotification extends Notification<Comment, CommentAction> {

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment payload;

    private CommentAction action;

}
