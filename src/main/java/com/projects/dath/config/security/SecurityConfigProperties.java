package com.projects.dath.config.security;

import com.projects.dath.model.User;
import com.projects.dath.repository.AdminRepository;
import com.projects.dath.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfigProperties {
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // TODO: temporarily allow all request => update security logic later
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            Optional<User> customer = customerRepository.findByUsername(username);
            return customer.orElseGet(() -> adminRepository.findByUsername(username).orElseThrow(
                    () -> new UsernameNotFoundException("Unknown user")
            ));
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        /*The adaptive one-way function BCrypt is used to encode a plain-text password
          to store in the database. The encoding takes 2^10 times to encode a password
         */
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationProvider authenticationProvider(){
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
}
