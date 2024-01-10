package com.projects.dath.model;

import com.projects.dath.constant.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@MappedSuperclass
public class User implements UserDetails {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false, unique = true) private String username;
    @Column(nullable = false) private String password;
    @CreationTimestamp private LocalDate createdDate;
    @Column(length = 20) private String fname;
    @Column(length = 20) private String lname;
    @Enumerated(EnumType.STRING) private Gender gender;
    @Enumerated(EnumType.STRING) @NonNull private UserRole role;
    private String phoneNumber;
    private LocalDate dob;
    private String email;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: fix the hard-code value "ROLE_CUSTOMER"
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+ this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
