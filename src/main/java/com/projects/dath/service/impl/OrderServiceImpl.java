package com.projects.dath.service.impl;

import com.projects.dath.exception.UnknownUser;
import com.projects.dath.model.Order;
import com.projects.dath.repository.OrderRepository;
import com.projects.dath.service.AccountService;
import com.projects.dath.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Primary
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AccountService accountService;
    @Override
    public Order checkout(Order order) {
        return null;
    }

    @Override
    public List<Order> getOrderHistory(UUID userId) {
        if(accountService.checkAccount(userId, true)){
           return orderRepository.findByOwnerId(userId);
        }
        throw new UnknownUser("Unknown Customer id: " + userId);

    }
}
