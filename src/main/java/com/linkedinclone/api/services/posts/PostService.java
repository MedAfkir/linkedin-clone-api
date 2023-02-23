package com.linkedinclone.api.services.posts;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.clients.ClientMapper;
import com.linkedinclone.api.dto.comments.CommentMapper;
import com.linkedinclone.api.dto.posts.*;
import com.linkedinclone.api.dto.comments.CommentDTO;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.PostNotFoundException;
import com.linkedinclone.api.models.posts.*;
import com.linkedinclone.api.models.clients.*;
import com.linkedinclone.api.models.comments.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ClientRepository clientRepository;
    private final CommentRepository commentRepository;

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final ClientMapper clientMapper;

    public List<PostSummaryDTO> getAll() {
        return postRepository
                .findAll()
                .stream()
                .map(postMapper::toPostSummaryDTO)
                .toList();
    }

    /**
     * Get Post by ID
     * @param id Id of post
     * @return Post
     * @throws PostNotFoundException Post not Found
     */
    public PostDTO getPostById(Long id) throws PostNotFoundException {
        return postMapper.toPostDTO(findPostById(id));
    }

    /**
     * Add a new post
     * @param request Post Request Body
     * @return Post added
     * @throws PostNotFoundException Post not found
     * @throws ClientNotFoundException Client not found
     */
    public PostDTO add(PostRequest request) throws PostNotFoundException, ClientNotFoundException {
        Client client = clientRepository
                .findById(request.clientId())
                .orElseThrow(ClientNotFoundException::new);
        Post post = new Post();
        post.setClient(client);
        post.setContent(request.content());
        Date creationDate = new Date();
        post.setCreatedAt(creationDate);
        post.setUpdatedAt(creationDate);
        return postMapper.toPostDTO(postRepository.save(post));
    }

    /**
     * Update a post
     * @param id Id of post that will be updated
     * @param request details to update
     * @return Post updated
     * @throws PostNotFoundException Post not found
     * @throws ClientNotFoundException Client not found
     */
    public PostDTO updatePost(Long id, PostRequest request) throws PostNotFoundException, ClientNotFoundException {
        Post post = findPostById(id);

        if (!post.getClient().getId().equals(request.clientId())) {
            Client client = clientRepository
                    .findById(request.clientId())
                    .orElseThrow(ClientNotFoundException::new);
            post.setClient(client);
        }

        post.setContent(request.content());
        post.setUpdatedAt(new Date());
        return postMapper.toPostDTO(postRepository.save(post));
    }

    /**
     * Delete a post by its ID
     * @param id Id of post that will be deleted
     * @throws PostNotFoundException Post Not Found
     */
    public void deleteById(Long id) throws PostNotFoundException {
        existsById(id);
        postRepository.deleteById(id);
    }

    /**
     * Get post comments
     * @param id Id of post whose comments will be fetched
     * @return List of post comments
     * @throws PostNotFoundException Post Not Found
     */
    public List<CommentDTO> getPostComments(Long id) throws PostNotFoundException {
        existsById(id);
        return commentRepository
                .findByPostId(id)
                .stream()
                .map(commentMapper::toCommentDTO)
                .toList();
    }

    /**
     * Get post owner ({@link Client})
     * @param id Id of post whose comments will be fetched
     * @return List of post comments
     * @throws PostNotFoundException Post Not Found
     */
    public ClientDTO getPostClient(Long id) throws PostNotFoundException {
        existsById(id);
        return clientMapper.toClientDTO(clientRepository.findByPostsId(id));
    }

    private Post findPostById(Long id) throws PostNotFoundException {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }

    private void existsById(Long id) throws PostNotFoundException {
        if (!postRepository.existsById(id))
            throw new PostNotFoundException();
    }
}
