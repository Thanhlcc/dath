package com.projects.dath.service;

import com.projects.dath.model.Customer;
import com.projects.dath.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AccountService {
    /**
     * `registerAccount` enables the registration of a new account (either admin or customer account)
     *
     * @param newUser represent for a new user
     * @return false if either email or username is not valid
     * * Customer account must not have registered another customer account that uses the same email and the username must be unique
     * * In other words, the customer with an existing account is eligible to register an admin account with the customer email
     */
    boolean registerAccount(User newUser);

    Customer getCustomer(UUID customerId);
    boolean checkAccount(UUID userId, boolean customerAcc);
}
