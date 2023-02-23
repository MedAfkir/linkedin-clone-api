package com.linkedinclone.api.request;

import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.exceptions.alreadyused.RequestAlreadySentException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.services.clients.ClientService;
import com.linkedinclone.api.services.friendrequest.FriendRequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RequestsTest {

    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    ClientService clientService;

    @Test
    void addRequest() throws ClientNotFoundException, RequestAlreadySentException {

        List<ClientSummaryDTO> followers = friendRequestService.getFollowers(6L);

        System.out.println(followers);
    }


}
