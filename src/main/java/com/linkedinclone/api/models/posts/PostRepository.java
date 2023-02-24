package com.linkedinclone.api.models.posts;

import com.linkedinclone.api.models.clients.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByClientId(Long id);
    Post findByCommentsId(Long id);

    List<Post> findByClientIn(Collection<Client> clients, Pageable pageable);



}
