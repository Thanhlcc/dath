package com.projects.dath.repository;

import com.projects.dath.model.ShoppingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingSessionRepository extends JpaRepository<ShoppingSession, Long> {
}
