package com.projects.dath.service;

import com.projects.dath.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface OrderService {
    Order checkout(Order order);
    List<Order> getOrderHistory(UUID userId);
}
