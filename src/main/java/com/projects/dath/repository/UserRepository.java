package com.projects.dath.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
