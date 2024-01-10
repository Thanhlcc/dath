package com.projects.dath.service;

import com.projects.dath.model.WishList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface WishListService {
    List<WishList> getContent(UUID customerId);

    List<WishList> addProduct(UUID customerId, Long productId);

    List<WishList> discardProduct(UUID customerId, Long productId);
}
