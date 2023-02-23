package com.linkedinclone.api.models.experiences;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRespository extends JpaRepository<Experience, Long> {
    List<Experience> findByClientId(Long id);
}
