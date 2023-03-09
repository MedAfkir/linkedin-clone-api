package com.linkedinclone.api.controllers.requests;

import com.linkedinclone.api.dto.requests.RequestCreateDTO;
import com.linkedinclone.api.dto.requests.RequestUpdateDTO;
import com.linkedinclone.api.exceptions.alreadyused.RequestAlreadySentException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.RequestNotFoundException;
import com.linkedinclone.api.models.requests.RequestState;
import com.linkedinclone.api.services.friendrequest.FriendRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1/friend-requests")
@RequiredArgsConstructor
public class RequestController {

    private final FriendRequestService friendRequestService;

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
    public ResponseEntity<?> createRequest(
            @Valid @RequestBody RequestCreateDTO request
    ) throws ClientNotFoundException, RequestAlreadySentException {
        return ResponseEntity.ok(friendRequestService.createRequest(request));
    }

    /**
     * Update the Friend Request {@link RequestState State}
     * @param requestDTO
     * @return
     * @throws RequestNotFoundException
     */
    @PutMapping("/request")
    public ResponseEntity<?> updateRequest(@RequestBody RequestUpdateDTO requestDTO)
            throws RequestNotFoundException {
        return ResponseEntity.ok(friendRequestService.updateRequest(requestDTO));
    }

    /**
     * Remove friend request
     * @param id ID of User invited to become (or already) a friend
     * @return
     * @throws RequestNotFoundException Friend Request Not Found
     */
    @DeleteMapping("/request/{id}")
    public ResponseEntity<?> removeRequest(@PathVariable("id") Long id)
            throws RequestNotFoundException {
        return ResponseEntity.ok(friendRequestService.removeRequest(id));
    }

}
