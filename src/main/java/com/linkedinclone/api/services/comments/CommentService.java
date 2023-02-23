package com.linkedinclone.api.services.comments;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.clients.ClientMapper;
import com.linkedinclone.api.dto.comments.CommentDTO;
import com.linkedinclone.api.dto.comments.CommentMapper;
import com.linkedinclone.api.dto.comments.CommentSummaryDTO;
import com.linkedinclone.api.dto.likes.comment.CommentLikeMapper;
import com.linkedinclone.api.dto.likes.comment.CommentLikeResponseDTO;
import com.linkedinclone.api.dto.posts.PostDTO;
import com.linkedinclone.api.dto.posts.PostMapper;
import com.linkedinclone.api.dto.posts.PostSummaryDTO;
import com.linkedinclone.api.exceptions.notfound.CommentNotFoundException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.PostNotFoundException;
import com.linkedinclone.api.dto.comments.CommentRequest;
import com.linkedinclone.api.models.comments.*;
import com.linkedinclone.api.models.clients.*;
import com.linkedinclone.api.models.posts.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ClientRepository clientRepository;
    private final PostRepository postRepository;

    private final CommentMapper commentMapper;
    private final ClientMapper clientMapper;
    private final PostMapper postMapper;

    private final CommentLikeMapper commentLikeMapper;

    /**
     * Get all comments
     * @return List of comments
     */
    public List<CommentSummaryDTO> getAllComments() {
        return commentRepository
                .findAll()
                .stream()
                .map(commentMapper::toCommentSummaryDTO)
                .toList();
    }

    /**
     * Get Comment by its ID
     * @param id Id of the comemnt that will be retrieved
     * @return Comment
     * @throws CommentNotFoundException Comment Not Found
     */
    public CommentDTO getCommentById(Long id) throws CommentNotFoundException {
        return commentMapper.toCommentDTO(findCommentById(id));
    }

    /**
     * Add new comment
     * @param request Comment Request Body
     * @return Comment added
     * @throws PostNotFoundException Post Not Found
     * @throws ClientNotFoundException Client Not Found
     */
    public CommentDTO addComment(CommentRequest request)
            throws ClientNotFoundException, PostNotFoundException {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(ClientNotFoundException::new);

        Post post = postRepository.findById(request.postId())
                .orElseThrow(PostNotFoundException::new);

        Date creationDate = new Date();
        Comment comment = Comment.builder()
                .content(request.content())
                .createdAt(creationDate)
                .updatedAt(creationDate)
                .client(client)
                .post(post)
                .build();
        return commentMapper.toCommentDTO(commentRepository.save(comment));
    }

    /**
     * Update Comment By its id
     * @param id Id of the comment that will be updated
     * @param request Comemnt Request Body
     * @return Comment updated
     * @throws PostNotFoundException Post Not Found
     * @throws ClientNotFoundException Client Not Found
     * @throws CommentNotFoundException Comment Client Not Found
     */
    public CommentDTO updateComment(Long id, CommentRequest request)
            throws CommentNotFoundException, ClientNotFoundException, PostNotFoundException {
        Comment comment = findCommentById(id);

        if (request.clientId().equals(comment.getClient().getId())) {
            Client client = clientRepository
                    .findById(request.clientId())
                    .orElseThrow(ClientNotFoundException::new);
            comment.setClient(client);
        }

        if (request.postId().equals(comment.getPost().getId())) {
            Post post = postRepository
                    .findById(request.postId())
                    .orElseThrow(PostNotFoundException::new);
            comment.setPost(post);
        }

        comment.setContent(request.content());
        return commentMapper.toCommentDTO(commentRepository.save(comment));
    }

    public void deleteComment(Long id) throws CommentNotFoundException {
        getCommentById(id);
        commentRepository.deleteById(id);
    }

    /**
     * Get Comment Writer ({@link Client})
     * @param id Id of the comment whose writer will be retrieved
     * @return Comment Writer
     * @throws CommentNotFoundException Comment Not Found
     */
    public ClientDTO getCommentClient(Long id) throws CommentNotFoundException {
        Comment comment = findCommentById(id);
        return clientMapper.toClientDTO(comment.getClient());
    }

    /**
     * Get Comment Post
     * @param id Id of the comment whose post will be retrieved
     * @return Comment's Post
     * @throws CommentNotFoundException Comment Not Found
     */
    public PostDTO getCommentPost(Long id) throws CommentNotFoundException {
        ifNotExistsThrowException(id);
        return postMapper.toPostDTO(postRepository.findByCommentsId(id));
    }

    private Comment findCommentById(Long id) throws CommentNotFoundException {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    private void ifNotExistsThrowException(Long id) throws CommentNotFoundException {
        if (!commentRepository.existsById(id))
            throw new CommentNotFoundException();
    }

    /**
     * @author adil
     * @param commentId
     * @return
     * @throws CommentNotFoundException
     */
    public List<CommentLikeResponseDTO> getAllLikesForComment(Long commentId)
            throws CommentNotFoundException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        return comment.getLikes().stream()
                .map(commentLikeMapper::toCLikeResponseDTO)
                .toList();
    }
}
