package com.linkedinclone.api.models.likes.post;

import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author adil
 */
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean findByClientAndPost(Client user, Post post);

}
