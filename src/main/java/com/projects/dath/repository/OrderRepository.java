package com.projects.dath.repository;

import com.projects.dath.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOwnerId(UUID userId);
}
