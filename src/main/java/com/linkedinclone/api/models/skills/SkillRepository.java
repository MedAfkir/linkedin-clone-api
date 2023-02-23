package com.linkedinclone.api.models.skills;

import com.linkedinclone.api.models.clients.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByClientsId(Long uid);
}
