package com.projects.dath.repository.impl;

import com.projects.dath.repository.AdminRepository;
import com.projects.dath.repository.CustomerRepository;
import com.projects.dath.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    @Override
    public boolean existsByUsername(String username) {
        return customerRepository.existsByUsername(username)
                && adminRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email)
                && adminRepository.existsByEmail(email);
    }
}
