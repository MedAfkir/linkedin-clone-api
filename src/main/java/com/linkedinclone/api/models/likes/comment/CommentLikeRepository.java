package com.linkedinclone.api.models.likes.comment;

import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.comments.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author adil
 */
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    boolean findByClientAndComment(Client user, Comment comment);

}
