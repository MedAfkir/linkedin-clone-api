package com.linkedinclone.api.controllers.posts;

import com.linkedinclone.api.dto.likes.post.PostLikeCreateDTO;
import com.linkedinclone.api.dto.likes.post.PostLikeUpdateDTO;
import com.linkedinclone.api.dto.posts.PostRequest;
import com.linkedinclone.api.dto.posts.PostSummaryDTO;
import com.linkedinclone.api.exceptions.alreadyused.AlreadyLikedException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.LikeNotFoundException;
import com.linkedinclone.api.exceptions.notfound.NotFoundException;
import com.linkedinclone.api.exceptions.notfound.PostNotFoundException;
import com.linkedinclone.api.services.likes.PostLikeService;
import com.linkedinclone.api.services.posts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostLikeService pLikeService;

    @GetMapping
    public ResponseEntity<List<PostSummaryDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") Long id)
            throws PostNotFoundException {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody PostRequest request) {
        try {
            return ResponseEntity.ok(postService.add(request));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostRequest request) {
        try {
            return ResponseEntity.ok(postService.updatePost(id, request));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        try {
            postService.deleteById(id);
            return ResponseEntity.ok().body(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getPostComments(@PathVariable("id") Long id) throws PostNotFoundException {
        return ResponseEntity.ok(postService.getPostComments(id));
    }

    @GetMapping("/{id}/client")
    public ResponseEntity<?> getPostClient(@PathVariable("id") Long id) throws PostNotFoundException {
        return ResponseEntity.ok(postService.getPostClient(id));
    }


    /**
     * @author adil
     * @param likeDTO
     * @return
     */
    @PostMapping("/like")
    public ResponseEntity<?> likePost(@RequestBody PostLikeCreateDTO likeDTO)
            throws ClientNotFoundException, AlreadyLikedException, PostNotFoundException {
        return ResponseEntity.ok(pLikeService.addPostLike(likeDTO));
    }


    /**
     * @author adil
     * @param id
     * @return
     */
    @DeleteMapping("/like/{id}")
    public ResponseEntity<?> deletePostLike(@PathVariable Long id)
            throws LikeNotFoundException {
        return ResponseEntity.ok(pLikeService.deletePostLike(id));
    }

    /**
     * @author adil
     * @param id
     * @param likeDTO
     * @return
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<?> updatePostLike(
            @PathVariable Long id,
            @RequestBody PostLikeUpdateDTO likeDTO
    ) throws LikeNotFoundException {
        return ResponseEntity.ok(pLikeService.updatePostLike(id, likeDTO));
    }


    /**
     * @author adil
     * @param postId
     * @return
     * @throws PostNotFoundException
     */
    @GetMapping("/{postId}/likes")
    public ResponseEntity<?> getAllLikesForPost(@PathVariable Long postId)
            throws PostNotFoundException {
        return ResponseEntity.ok(postService.getAllLikesForPost(postId));
    }


}
