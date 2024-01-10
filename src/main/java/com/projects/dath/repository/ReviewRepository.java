package com.projects.dath.repository;

import com.projects.dath.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Iterable<Review> findTop10ByProductId(Long productId);
}
