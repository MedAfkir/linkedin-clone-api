package com.linkedinclone.api.services.posts;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.clients.ClientMapper;
import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.dto.comments.CommentMapper;
import com.linkedinclone.api.dto.likes.post.PostLikeMapper;
import com.linkedinclone.api.dto.likes.post.PostLikeResponseDTO;
import com.linkedinclone.api.dto.posts.*;
import com.linkedinclone.api.dto.comments.CommentDTO;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.PostNotFoundException;
import com.linkedinclone.api.models.likes.LikeType;
import com.linkedinclone.api.models.likes.post.PostLike;
import com.linkedinclone.api.models.posts.*;
import com.linkedinclone.api.models.clients.*;
import com.linkedinclone.api.models.comments.*;
import com.linkedinclone.api.services.friendrequest.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ClientRepository clientRepository;
    private final CommentRepository commentRepository;

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final ClientMapper clientMapper;

    private final PostLikeMapper postLikeMapper;

    private final FriendRequestService friendRequestService;

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


    /**
     * @author adil
     * @param postId
     * @return
     * @throws PostNotFoundException
     */
    public List<PostLikeResponseDTO> getAllLikesForPost(Long postId)
            throws PostNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        return post.getLikes().stream()
                .map(postLikeMapper::toPostLikeResponseDTO)
                .toList();
    }


    /**
     * get a fixed size of friends's posts
     * @param clientId id of the user that he asks for posts
     * @param page page number
     * @param size size of the page
     * @return
     */
    public List<PostResponseDTO> getPostsOfFollowings(Long clientId, int page, int size)
            throws ClientNotFoundException {
        clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        PageRequest pageRequest = PageRequest.of(
                page, size, Sort.by("createdAt").descending());
        List<Client> followings = friendRequestService.getFollowingsList(clientId);
        List<Post> posts = postRepository.findByClientIn(followings, pageRequest);
        List<PostResponseDTO> postResponseDTOS = new ArrayList<>();
        for (Post post : posts) {
            PostResponseDTO postResponseDTO = postMapper.toPostResponseDTO(post);
            postResponseDTO.setFriendComment(getFriendComment(post,
                    followings.stream()
                            .map(clientMapper::toClientSummaryDTO)
                            .toList()
                    ));
            postResponseDTO.setLikeTypes(getSortedLikeTypes(post));

            postResponseDTOS.add(postResponseDTO);

        }
        return postResponseDTOS;
    }

    private CommentDTO getFriendComment(Post post, List<ClientSummaryDTO> followings) {
        List<CommentDTO> commentDTOList = post.getComments().stream()
                .map(commentMapper::toCommentDTO)
                .filter(commentDTO -> followings.contains(commentDTO.getClient()))
                .toList();
        return commentDTOList.isEmpty() ? null : commentDTOList.get(0);

    }


    private List<LikeType> getSortedLikeTypes(Post post){
        Map<LikeType, Integer> likeCounts = new HashMap<>();
        for(PostLike like : post.getLikes()) {
            LikeType likeType = like.getType();
            likeCounts.put(likeType, likeCounts.getOrDefault(likeType, 0) + 1);
        }
        List<Map.Entry<LikeType, Integer>> sortedLikeCounts = new ArrayList<>(likeCounts.entrySet());
        sortedLikeCounts.sort(Map.Entry.<LikeType, Integer>comparingByValue().reversed());
        List<LikeType> sortedLikeTypes = new ArrayList<>();
        for (Map.Entry<LikeType, Integer> entry : sortedLikeCounts) {
            sortedLikeTypes.add(entry.getKey());
        }
        return sortedLikeTypes;
    }
}
