package com.project.event_management_system.service.implementation;

import com.project.event_management_system.enums.EmailVerificationStatus;
import com.project.event_management_system.enums.Role;
import com.project.event_management_system.model.User;
import com.project.event_management_system.repository.UserRepository;
import com.project.event_management_system.security.UserPrinciple;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));


        boolean isEmailVerified = checkEmailVerificationStatus(user);


        return new UserPrinciple(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user.getRoles()), // Pass user roles
                isEmailVerified // Pass email verification status
        );


    }

    private boolean checkEmailVerificationStatus(User user){
        return user.getEmailVerificationStatus() == EmailVerificationStatus.VERIFIED;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet()); // Supports multiple roles return Set.of((new SimpleGrantedAuthority("ROLE_" + roles.name()))); // Example: ROLE_USER, ROLE_ORGANIZER
    }


}
