package com.linkedinclone.api.controllers.clients;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.dto.requests.RequestCreateDTO;
import com.linkedinclone.api.dto.requests.RequestUpdateDTO;
import com.linkedinclone.api.exceptions.alreadyused.RequestAlreadyAcceptedException;
import com.linkedinclone.api.exceptions.alreadyused.RequestAlreadySentException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.RequestNotFoundException;
import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.models.requests.Request;
import com.linkedinclone.api.models.requests.RequestState;
import com.linkedinclone.api.services.clients.ClientService;
import com.linkedinclone.api.services.friendrequest.FriendRequestService;
import com.linkedinclone.api.services.posts.PostService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final FriendRequestService friendRequestService;
    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(
            @PathVariable("id") Long id,
            @RequestBody ClientDTO clientDTO
    ) {
        return null; // TODO
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<?> getClientSkills(@PathVariable("id") Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(clientService.getClientSkills(id));
    }

    @GetMapping("/{id}/experiences")
    public ResponseEntity<?> getClientExperiences(
            @PathVariable("id") Long id
    ) throws ClientNotFoundException {
        return ResponseEntity.ok(clientService.getClientExperiences(id));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<?> getClientPosts(@PathVariable("id") Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(clientService.getClientPosts(id));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getClientComments(@PathVariable("id") Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(clientService.getClientComments(id));
    }

    @GetMapping("/{id}/position")
    public ResponseEntity<?> getClientPosition(@PathVariable("id") Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(clientService.getClientPosition(id));
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<?> getClientFollowers(@PathVariable("id") Long id)
            throws ClientNotFoundException {
        return ResponseEntity.ok(friendRequestService.getFollowers(id));
    }

    @GetMapping("/{id}/followings")
    public ResponseEntity<?> getClientFollowings(@PathVariable("id") Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(friendRequestService.getFollowings(id));
    }

    /**
     * Get All {@link Request Friend Requests}s
     *
     * @param id ID of {@link Client} that sent the friend requests
     * @return List of {@link ClientSummaryDTO Client}s who receive friend request fro mthe sender
     * @throws ClientNotFoundException
     */
    @GetMapping("/{id}/requests")
    public ResponseEntity<?> getRequests(@PathVariable("id") Long id)
            throws ClientNotFoundException {
        return ResponseEntity.ok(friendRequestService.getRequests(id));
    }

    /**
     * Get all the requests that the user send but not accepted yet
     *
     * @param id
     * @return
     * @throws ClientNotFoundException
     */
    @GetMapping("/{id}/pending-requests")
    public ResponseEntity<?> getPendingRequests(@PathVariable("id") Long id)
            throws ClientNotFoundException {
        return ResponseEntity.ok(friendRequestService.getSentRequests(id));
    }

    /**
     * send friend request to a user (request body contains id of the sender and the id
     * of the receiver
     *
     * @param request
     * @return
     * @throws ClientNotFoundException
     * @throws RequestAlreadySentException
     */
    @PostMapping("/request")
    public ResponseEntity<?> createRequest(@RequestBody RequestCreateDTO request)
            throws ClientNotFoundException, RequestAlreadySentException {
        return ResponseEntity.ok(friendRequestService.createRequest(request));
    }

    /**
     * Update the Friend Request {@link RequestState State}
     *
     * @param requestDTO
     * @return
     * @throws RequestAlreadyAcceptedException
     * @throws RequestNotFoundException
     */
    @PutMapping("/request")
    public ResponseEntity<?> updateRequest(@RequestBody RequestUpdateDTO requestDTO)
            throws RequestNotFoundException {
        return ResponseEntity.ok(friendRequestService.updateRequest(requestDTO));
    }

    /**
     * remove friend request
     *
     * @param id ID of User invited to become (or already) a friend
     * @return
     * @throws RequestNotFoundException Friend Request Not Found
     */
    @DeleteMapping("/request/{id}")
    public ResponseEntity<?> removeRequest(@PathVariable("id") Long id)
            throws RequestNotFoundException {
        return ResponseEntity.ok(friendRequestService.removeRequest(id));
    }

    @GetMapping("/{clientId}/followings/posts")
    public ResponseEntity<?> getFollowingsPosts(
            @PathVariable Long clientId,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) throws ClientNotFoundException {
        return ResponseEntity.ok(postService.getPostsOfFollowings(clientId, page, size));
    }

}
