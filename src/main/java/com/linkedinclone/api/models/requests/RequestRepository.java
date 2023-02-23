package com.linkedinclone.api.models.requests;

import com.linkedinclone.api.models.clients.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {


    /**
     * check if the request was already created between two users
     * @param sender
     * @param receiver
     * @return
     */
    boolean existsBySenderAndReceiver(Client sender, Client receiver);

    /**
     * find all the received requests of a specific user
     * note : if we specify state = ACCEPTED => we get user's followers
     * @param receiver
     * @param state
     * @return
     */
    List<Request> findByReceiverAndState(Client receiver, RequestState state);

    /**
     * find all the send requests of a specific user
     * @param sender
     * @param state
     * @return
     */
    List<Request> findBySenderAndState(Client sender, RequestState state);

    Optional<Request> findBySenderAndReceiver(Client sender, Client receiver);


    @Modifying
    @Query("UPDATE Request r SET r.state = :newState WHERE r.id = :requestId")
    void updateRequestState(@Param("requestId") Long requestId, @Param("newState") RequestState newState);



}
