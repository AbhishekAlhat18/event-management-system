package com.project.event_management_system.service.implementation;

import com.project.event_management_system.enums.EmailVerificationStatus;
import com.project.event_management_system.enums.Role;
import com.project.event_management_system.model.Person;
import com.project.event_management_system.repository.PersonRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Check if email is verified
        if (person.getEmailVerificationStatus() != EmailVerificationStatus.VERIFIED) {
            throw new DisabledException("Email not verified. Please activate your account.");
        }

        return new org.springframework.security.core.userdetails.User(
                person.getEmail(),
                person.getPassword(),
                getAuthorities(person.getRole())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return Set.of((new SimpleGrantedAuthority("ROLE_" + role.name()))); // Example: ROLE_USER, ROLE_ORGANIZER
    }

}
