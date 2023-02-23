package com.linkedinclone.api.exceptions.notfound;

import com.linkedinclone.api.models.posts.Post;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(String message) {
        super(Post.class, message);
    }

    public PostNotFoundException() {
        this("Post Not Found");
    }
}
