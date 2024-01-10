package com.projects.dath.controller;

import com.projects.dath.model.User;
import com.projects.dath.model.UserRole;
import com.projects.dath.dto.RegisterReq;
import com.projects.dath.mapper.AccountMapper;
import com.projects.dath.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AuthenticationController {
    private final AccountService accountService;
    private final AccountMapper mapper;

    @PostMapping("/{role:admin|customer}")
    ResponseEntity<String> registerAdminAccount(
            @Valid @RequestBody RegisterReq request,
            @PathVariable("role") String role
    ) {
        User user = null;
        switch (UserRole.valueOf(role.toUpperCase())) {
            case ADMIN -> user = mapper.toAdmin(request);
            case CUSTOMER -> user = mapper.toCustumer(request);
        }
        if (accountService.registerAccount(user)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Account Created");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username or Password has already been taken");
        }
    }
}
