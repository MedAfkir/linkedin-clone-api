package com.linkedinclone.api.controllers.clients;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.requests.RequestCreateDTO;
import com.linkedinclone.api.dto.requests.RequestUpdateDTO;
import com.linkedinclone.api.exceptions.alreadyused.RequestAlreadyAcceptedException;
import com.linkedinclone.api.exceptions.alreadyused.RequestAlreadySentException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.NotFoundException;
import com.linkedinclone.api.exceptions.notfound.RequestNotFoundException;
import com.linkedinclone.api.services.clients.ClientService;
import com.linkedinclone.api.services.friendrequest.FriendRequestService;
import com.linkedinclone.api.services.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {
    private final ClientService clientService;
    private final FriendRequestService friendRequestService;

    private final PostService postService;

    @Autowired
    public ClientController(ClientService clientService, FriendRequestService friendRequestService, PostService postService) {
        this.clientService = clientService;
        this.friendRequestService = friendRequestService;
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(clientService.getClientById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO) {
        return null; // TODO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        try {
            clientService.deleteClientById(id);
            return ResponseEntity.ok(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<?> getClientSkills(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(clientService.getClientSkills(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/experiences")
    public ResponseEntity<?> getClientExperiences(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(clientService.getClientExperiences(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<?> getClientPosts(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(clientService.getClientPosts(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getClientComments(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(clientService.getClientComments(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/position")
    public ResponseEntity<?> getClientPosition(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(clientService.getClientPosition(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
     * get all the friend requests
     *
     * @param id
     * @return
     * @throws ClientNotFoundException
     */
    @GetMapping("/{id}/requests")
    public ResponseEntity<?> getRequests(@PathVariable("id") Long id)
            throws ClientNotFoundException {
        return ResponseEntity.ok(friendRequestService.getRequests(id));
    }

    /**
     * get all the requests that the user send but not accepted yet
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
     * update the friend the request
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
     * @param id
     * @return
     * @throws RequestNotFoundException
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
