package com.linkedinclone.api.services.likes;

import com.linkedinclone.api.dto.likes.comment.*;
import com.linkedinclone.api.dto.likes.post.PostLikeUpdateDTO;
import com.linkedinclone.api.exceptions.alreadyused.AlreadyLikedException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.CommentNotFoundException;
import com.linkedinclone.api.exceptions.notfound.LikeNotFoundException;
import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.clients.ClientRepository;
import com.linkedinclone.api.models.comments.Comment;
import com.linkedinclone.api.models.comments.CommentRepository;
import com.linkedinclone.api.models.likes.comment.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author adil
 */
@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final ClientRepository clientRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentLikeMapper commentLikeMapper;

    public CommentLikeDTO addCommentLike(CommentLikeCreateDTO likeDTO) throws CommentNotFoundException,
            ClientNotFoundException, AlreadyLikedException {
        Comment comment = commentRepository.findById(likeDTO.getCommentId())
                .orElseThrow(CommentNotFoundException::new);
        Client client = clientRepository.findById(likeDTO.getUserId())
                .orElseThrow(ClientNotFoundException::new);

        if (commentLikeRepository.findByClientAndComment(client, comment))
            throw new AlreadyLikedException();

        CommentLike like = CommentLike.builder()
                .client(client)
                .comment(comment)
                .type(likeDTO.getType())
                .build();

        return commentLikeMapper.toCommentLikeDTO(commentLikeRepository.save(like));
    }

    public CommentLikeDTO updateCommentLike(Long id, PostLikeUpdateDTO likeDTO)
            throws LikeNotFoundException {
        CommentLike like = findCommentLikeById(id);
        like.setType(likeDTO.getType());
        return commentLikeMapper.toCommentLikeDTO(commentLikeRepository.save(like));
    }

    public CommentLikeDTO deletePLike(Long id) throws LikeNotFoundException {
        CommentLike like = findCommentLikeById(id);
        commentLikeRepository.delete(like);
        return commentLikeMapper.toCommentLikeDTO(like);
    }

    /**
     *
     * Get Comment Like By its ID
     *
     * @author afkir
     *
     * @param id ID of the like that will be fetched
     * @return Get Comment Like
     * @throws LikeNotFoundException Like Not Found
     */
    private CommentLike findCommentLikeById(Long id) throws LikeNotFoundException {
        return commentLikeRepository.findById(id).orElseThrow(LikeNotFoundException::new);
    }
}
