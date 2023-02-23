package com.linkedinclone.api.controllers.comments;

import com.linkedinclone.api.dto.comments.*;
import com.linkedinclone.api.dto.likes.comment.*;
import com.linkedinclone.api.dto.likes.post.*;
import com.linkedinclone.api.dto.posts.PostDTO;
import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.exceptions.alreadyused.AlreadyLikedException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.CommentNotFoundException;
import com.linkedinclone.api.exceptions.notfound.LikeNotFoundException;
import com.linkedinclone.api.exceptions.notfound.NotFoundException;
import com.linkedinclone.api.services.likes.CommentLikeService;
import com.linkedinclone.api.services.comments.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentLikeService cLikeService;

    @GetMapping
    public ResponseEntity<List<CommentSummaryDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping
    public ResponseEntity<?> updateComment(@RequestBody CommentRequest request) throws NotFoundException {
        return ResponseEntity.ok(commentService.addComment(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable("id") Long id,
            @RequestBody CommentRequest request)
            throws NotFoundException {
        return ResponseEntity.ok(commentService.updateComment(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) throws NotFoundException {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<ClientDTO> getCommentUser(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(commentService.getCommentClient(id));
    }

    @GetMapping("/{id}/post")
    public ResponseEntity<PostDTO> getCommentPost(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(commentService.getCommentPost(id));
    }


    /**
     * @author adil
     * @param likeDTO
     * @return
     */
    @PostMapping("/like")
    public ResponseEntity<?> likeComment(@RequestBody CommentLikeCreateDTO likeDTO)
            throws ClientNotFoundException, AlreadyLikedException, CommentNotFoundException {
        return ResponseEntity.ok(cLikeService.addCommentLike(likeDTO));
    }

    @DeleteMapping("/like/{id}")
    public ResponseEntity<?> deleteCommentLike(@PathVariable Long id) throws LikeNotFoundException {
        return ResponseEntity.ok(cLikeService.deletePLike(id));
    }

    @PutMapping("/like/{id}")
    public ResponseEntity<?> updateCommentLike(
            @PathVariable Long id,
            @RequestBody PostLikeUpdateDTO likeDTO
    ) throws LikeNotFoundException {
        return ResponseEntity.ok(cLikeService.updateCommentLike(id, likeDTO));
    }

    @GetMapping("/{commentId}/likes")
    public ResponseEntity<?> getAllLikesForComment(@PathVariable Long commentId)
            throws CommentNotFoundException {
        return ResponseEntity.ok(commentService.getAllLikesForComment(commentId));
    }

}
