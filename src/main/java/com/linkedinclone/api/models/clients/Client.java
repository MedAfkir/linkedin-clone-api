package com.linkedinclone.api.models.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkedinclone.api.models.likes.comment.CommentLike;
import com.linkedinclone.api.models.likes.post.PostLike;
import com.linkedinclone.api.models.roles.Role;
import com.linkedinclone.api.models.comments.Comment;
import com.linkedinclone.api.models.experiences.Experience;
import com.linkedinclone.api.models.positions.Position;
import com.linkedinclone.api.models.posts.Post;
import com.linkedinclone.api.models.skills.Skill;
import com.linkedinclone.api.models.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    @JsonIgnore
    private Position position;

    @ManyToMany(mappedBy = "clients", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Skill> skills;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Experience> experiences;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostLike> pLikes;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommentLike> cLikes;

    private boolean privateAccount = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(Role.USER);
    }
}
