package com.linkedinclone.api.models.clients;

import com.linkedinclone.api.models.posts.Post;
import com.linkedinclone.api.models.skills.Skill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findBySkillsId(Long id);
    List<Client> findByPositionId(Long id);
    Client findByExperiencesId(Long id);
    Client findByCommentsId(Long id);
    Client findByPostsId(Long id);
    Optional<Client> findByUsername(String username);
    Optional<Client> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


}
