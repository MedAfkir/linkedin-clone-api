package com.linkedinclone.api.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkedinclone.api.models.clients.Client;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Client sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Client receiver;

    @Enumerated(EnumType.STRING)
    private RequestState state;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sentAt;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acceptedAt;

}
