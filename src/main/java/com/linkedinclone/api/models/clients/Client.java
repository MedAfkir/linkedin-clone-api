package com.linkedinclone.api.models.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkedinclone.api.models.likes.comment.CommentLike;
import com.linkedinclone.api.models.likes.post.PostLike;
import com.linkedinclone.api.models.requests.Request;
import com.linkedinclone.api.models.requests.RequestState;
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

    private boolean privateAccount = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    @JsonIgnore
    private Position position;

    @ManyToMany(mappedBy = "clients", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Skill> skills;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Experience> experiences;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommentLike> commentLikes;

    @OneToMany(mappedBy = "sender")
    private Collection<Request> requestsSent;

    @OneToMany(mappedBy = "receiver")
    private Collection<Request> requestsReceived;

    @Transient
    private int followersLength;

    @Transient
    private int followingLength;

    public int getFollowersLength() {
        return requestsSent.stream()
                .filter(request -> request.getState().equals(RequestState.ACCEPTED))
                .toList()
                .size();
    }

    public int getFollowingLength() {
        return requestsReceived.stream()
                .filter(request -> request.getState().equals(RequestState.ACCEPTED))
                .toList()
                .size();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(Role.USER);
    }
}
