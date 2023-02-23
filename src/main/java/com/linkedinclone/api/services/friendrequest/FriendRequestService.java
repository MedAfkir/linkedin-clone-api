package com.linkedinclone.api.services.friendrequest;

import com.linkedinclone.api.dto.clients.ClientMapper;
import com.linkedinclone.api.dto.clients.ClientSummaryDTO;
import com.linkedinclone.api.dto.requests.*;
import com.linkedinclone.api.exceptions.alreadyused.RequestAlreadyAcceptedException;
import com.linkedinclone.api.exceptions.alreadyused.RequestAlreadySentException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.exceptions.notfound.RequestNotFoundException;
import com.linkedinclone.api.models.clients.*;
import com.linkedinclone.api.models.requests.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author adil
 */
@Service
@RequiredArgsConstructor
public class FriendRequestService {

    private final ClientRepository clientRepository;
    private final RequestRepository requestRepository;
    private final ClientMapper clientMapper;
    private final RequestMapper requestMapper;

    /**
     * get the followers of a specific user
     * @param userId
     * @return
     */
    public List<ClientSummaryDTO> getFollowers(Long userId) throws ClientNotFoundException {

        Client user = clientRepository.findById(userId)
                .orElseThrow(ClientNotFoundException::new);

        List<Request> followersRequests = requestRepository.findByReceiverAndState(user, RequestState.ACCEPTED);
        return followersRequests.stream()
                .map(request -> clientMapper.toClientSummaryDTO(request.getSender()))
                .toList();
    }

    /**
     * Get followings users of a specific user -- (the users that he is following)
     * @param userId
     * @return List of Follwing Users ({@link Client})
     */
    public List<ClientSummaryDTO> getFollowings(Long userId) throws ClientNotFoundException {
        Client user = clientRepository.findById(userId)
                .orElseThrow(ClientNotFoundException::new);

        List<Request> followersRequests = requestRepository.
                findBySenderAndState(user, RequestState.ACCEPTED);
        return followersRequests.stream()
                .map(request -> clientMapper.toClientSummaryDTO(request.getReceiver()))
                .toList();
    }

    /**
     * get follow requests (friend requests)
     * @param userId
     * @return
     */
    public List<ClientSummaryDTO> getRequests(Long userId) throws ClientNotFoundException {

        Client user = clientRepository.findById(userId)
                .orElseThrow(ClientNotFoundException::new);

        List<Request> followersRequests = requestRepository
                .findByReceiverAndState(user, RequestState.PENDING);
        return followersRequests.stream()
                .map(request -> clientMapper.toClientSummaryDTO(request.getSender()))
                .toList();
    }

    /**
     * get sent requests (all the requests that the user sent but not accepted yet)
     * @param userId
     * @return
     */
    public List<ClientSummaryDTO> getSentRequests(Long userId) throws ClientNotFoundException {

        Client user = clientRepository.findById(userId)
                .orElseThrow(ClientNotFoundException::new);

        List<Request> followersRequests = requestRepository
                .findBySenderAndState(user, RequestState.PENDING);
        return followersRequests.stream()
                .map(request -> clientMapper.toClientSummaryDTO(request.getReceiver()))
                .toList();
    }

    public RequestDTO createRequest(RequestCreateDTO requestDTO) throws RequestAlreadySentException, ClientNotFoundException {
        Client sender = clientRepository.findById(requestDTO.getSenderId())
                .orElseThrow(ClientNotFoundException::new);
        Client receiver = clientRepository.findById(requestDTO.getReceiverId())
                .orElseThrow(ClientNotFoundException::new);
        if(requestRepository.existsBySenderAndReceiver(sender, receiver))
            throw new RequestAlreadySentException();

        Request request = Request.builder()
                .sender(sender)
                .receiver(receiver)
                .state(receiver.isPrivateAccount() ? RequestState.PENDING : RequestState.ACCEPTED)
                .sentAt(new Date())
                .acceptedAt(receiver.isPrivateAccount() ? null : new Date())
                .build();
        return requestMapper.toRequestDTO(requestRepository.save(request));
    }

    /**
     * update the request ( set request state to accepted or refused )
     * @param requestDTO
     * @return
     * @throws RequestNotFoundException
     * @throws RequestAlreadyAcceptedException
     */
    public RequestDTO updateRequest(RequestUpdateDTO requestDTO)
            throws RequestNotFoundException {
        Request request = requestRepository.findById(requestDTO.getRequestId())
                .orElseThrow(RequestNotFoundException::new);

        request.setState(requestDTO.getRequestState());
        if(requestDTO.getRequestState().isAccepted()) request.setAcceptedAt(new Date());

        return requestMapper.toRequestDTO(requestRepository.save(request));
    }

    /**
     * remove request
     * note : usage -> unfollow a user
     * @param requestId
     * @return
     * @throws RequestNotFoundException
     */
    public RequestDTO removeRequest(Long requestId) throws RequestNotFoundException {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(RequestNotFoundException::new);

        requestRepository.delete(request);
        return requestMapper.toRequestDTO(request);
    }

    public RequestDTO findBySenderAndReceiver(Long senderId, Long receiverId) throws RequestNotFoundException {
        Client sender = clientRepository.findById(senderId).orElse(null);
        Client receiver = clientRepository.findById(receiverId).orElse(null);
        Request request = requestRepository.findBySenderAndReceiver(sender, receiver)
                .orElseThrow(RequestNotFoundException::new);

        return requestMapper.toRequestDTO(request);
    }

}
