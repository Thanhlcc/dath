package com.projects.dath.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id private Long id;
    @Column(nullable = false) private String street;
    @Column(nullable = false) private String zipCode;
    @Column(nullable = false) private String city;
    @ManyToMany
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "owner", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address", referencedColumnName = "id"
        ))
    private Set<Customer> owners = new HashSet();
}
