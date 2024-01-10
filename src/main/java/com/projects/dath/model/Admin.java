package com.projects.dath.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.*;

/**
 * Temporarily omit role
 * TODO: add role property when implementing security
 */

@Getter
@Entity
@NoArgsConstructor
public class Admin extends User {
    @GeneratedValue
    private String employeeId;
    public Admin(String employeeId) {
        super();
        this.employeeId = employeeId;
        this.setRole(UserRole.ADMIN);
    }
}
