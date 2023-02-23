package com.linkedinclone.api.models.likes.comment;

import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.comments.Comment;
import com.linkedinclone.api.models.likes.LikeType;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author adil
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LikeType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Client client;
}
