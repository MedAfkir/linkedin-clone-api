package com.linkedinclone.api.models.admins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findByApproval(Admin.Approval approval);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Admin> findByUsername(String username);
}
