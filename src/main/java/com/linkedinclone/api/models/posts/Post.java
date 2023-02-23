package com.linkedinclone.api.models.posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.comments.Comment;
import com.linkedinclone.api.models.likes.post.PostLike;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Collection<PostLike> likes;
}
