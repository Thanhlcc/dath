package com.projects.dath.repository;

import com.projects.dath.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
