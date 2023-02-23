package com.linkedinclone.api.exceptions.notfound;

import com.linkedinclone.api.models.comments.Comment;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(String message) {
        super(Comment.class, message);
    }

    public CommentNotFoundException() {
        this("Comment Not Found");
    }
}
