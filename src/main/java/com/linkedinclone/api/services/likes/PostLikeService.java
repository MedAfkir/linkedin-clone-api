package com.linkedinclone.api.services.likes;

import com.linkedinclone.api.dto.likes.post.*;
import com.linkedinclone.api.exceptions.alreadyused.AlreadyLikedException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.LikeNotFoundException;
import com.linkedinclone.api.exceptions.notfound.PostNotFoundException;
import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.clients.ClientRepository;
import com.linkedinclone.api.models.likes.post.PostLike;
import com.linkedinclone.api.models.likes.post.PostLikeRepository;
import com.linkedinclone.api.models.posts.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author adil
 */
@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final ClientRepository clientRepository;
    private final PostRepository postRepository;
    private final PostLikeMapper postLikeMapper;

    public PostLikeDTO addPostLike(PostLikeCreateDTO likeDTO)
            throws PostNotFoundException, ClientNotFoundException, AlreadyLikedException {
        Post post = postRepository.findById(likeDTO.getPostId())
                .orElseThrow(PostNotFoundException::new);
        Client client = clientRepository.findById(likeDTO.getUserId())
                .orElseThrow(ClientNotFoundException::new);

        if (postLikeRepository.findByClientAndPost(client, post))
            throw new AlreadyLikedException();

        PostLike like = PostLike.builder()
                .client(client)
                .post(post)
                .type(likeDTO.getType())
                .build();

        return postLikeMapper.toPostLikeDTO(postLikeRepository.save(like));
    }

    public PostLikeDTO updatePostLike(Long id, PostLikeUpdateDTO likeDTO)
            throws LikeNotFoundException {
        PostLike like = findPostLikeById(id);
        like.setType(likeDTO.getType());
        return postLikeMapper.toPostLikeDTO(postLikeRepository.save(like));
    }

    public PostLikeDTO deletePostLike(Long id) throws LikeNotFoundException {
        PostLike like = findPostLikeById(id);
        postLikeRepository.delete(like);
        return postLikeMapper.toPostLikeDTO(like);
    }

    private PostLike findPostLikeById(Long id) throws LikeNotFoundException {
        return postLikeRepository.findById(id).orElseThrow(LikeNotFoundException::new);
    }

}
