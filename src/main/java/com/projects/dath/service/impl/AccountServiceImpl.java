package com.projects.dath.service.impl;

import com.projects.dath.exception.UnknownUser;
import com.projects.dath.model.Admin;
import com.projects.dath.model.Customer;
import com.projects.dath.model.User;
import com.projects.dath.repository.AdminRepository;
import com.projects.dath.repository.CustomerRepository;
import com.projects.dath.repository.UserRepository;
import com.projects.dath.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Primary
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public boolean registerAccount(User newUser) {
        // TODO: validate user email address existence
        var username = newUser.getUsername();
        var email = newUser.getEmail();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        if (newUser instanceof Customer
                && !userRepository.existsByUsername(username)
                && !customerRepository.existsByEmail(email)
        ) {
            customerRepository.save((Customer) newUser);
        }
        else if(newUser instanceof Admin
                && !userRepository.existsByUsername(username)
                && !adminRepository.existsByEmail(email)
        ){
            adminRepository.save((Admin) newUser);
        }
        else return false;
        return true;
    }

    @Override
    public Customer getCustomer(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow((() -> new UnknownUser("unknown user id: " + customerId)));
    }

    @Override
    public boolean checkAccount(UUID userId, boolean customerAcc) {
        return customerAcc
                ? customerRepository.existsById(userId)
                : adminRepository.existsById(userId);
    }
}
