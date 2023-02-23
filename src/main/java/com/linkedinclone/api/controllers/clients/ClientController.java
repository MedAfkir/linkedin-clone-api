package com.linkedinclone.api.controllers.clients;

import com.linkedinclone.api.dto.clients.ClientDTO;
import com.linkedinclone.api.dto.requests.RequestCreateDTO;
import com.linkedinclone.api.dto.requests.RequestUpdateDTO;
import com.linkedinclone.api.exceptions.alreadyused.AlreadyUsedException;
import com.linkedinclone.api.exceptions.notfound.NotFoundException;
import com.linkedinclone.api.services.clients.ClientService;
import com.linkedinclone.api.services.friendrequest.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {
    private final ClientService clientService;
    private final FriendRequestService friendRequestService;

    @Autowired
    public ClientController(ClientService clientService, FriendRequestService friendRequestService) {
        this.clientService = clientService;
        this.friendRequestService = friendRequestService;
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
    public ResponseEntity<?> getClientFollowers(@PathVariable("id") Long id){
       try {
           return ResponseEntity.ok(friendRequestService.getFollowers(id));
       }catch (NotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
    }

    @GetMapping("/{id}/followings")
    public ResponseEntity<?> getClientFollowings(@PathVariable("id") Long id){
       try {
           return ResponseEntity.ok(friendRequestService.getFollowings(id));
       }catch (NotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
    }


    @GetMapping("/{id}/requests")
    public ResponseEntity<?> getRequests(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(friendRequestService.getRequests(id));
        }catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/pending-requests")
    public ResponseEntity<?> getPendingRequests(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(friendRequestService.getSentRequests(id));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/request")
    public ResponseEntity<?> createRequest(@RequestBody RequestCreateDTO request) {
        try {
            return ResponseEntity.ok(friendRequestService.createRequest(request));

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (AlreadyUsedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("/request")
    public ResponseEntity<?> updateRequest(@RequestBody RequestUpdateDTO requestDTO) {
        try {
            return ResponseEntity.ok(friendRequestService.updateRequest(requestDTO));
        } catch (AlreadyUsedException e) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/request/{id}")
    public ResponseEntity<?> removeRequest(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(friendRequestService.removeRequest(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}