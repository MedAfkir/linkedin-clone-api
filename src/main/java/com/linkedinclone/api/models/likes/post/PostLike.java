package com.linkedinclone.api.models.likes.post;

import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.likes.LikeType;
import com.linkedinclone.api.models.posts.Post;
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
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LikeType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Client client;


}
