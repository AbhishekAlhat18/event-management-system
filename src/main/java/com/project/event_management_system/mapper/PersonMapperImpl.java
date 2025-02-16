package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.OrganizerRegistrationDTO;
import com.project.event_management_system.dto.UserRegistrationDTO;
import com.project.event_management_system.enums.Role;
import com.project.event_management_system.model.Organizer;
import com.project.event_management_system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PersonMapperImpl implements PersonMapper {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public User toUserEntity(UserRegistrationDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // Hash password before saving
        user.setContactNumber(dto.getContactNumber());
        user.setRole(Role.USER);
        return user;
    }

    public Organizer toOrganizerEntity(OrganizerRegistrationDTO dto) {
        Organizer organizer = new Organizer();
        organizer.setFirstName(dto.getFirstName());
        organizer.setLastName(dto.getLastName());
        organizer.setEmail(dto.getEmail());
        organizer.setPassword(passwordEncoder.encode(dto.getPassword())); // Hash password before saving
        organizer.setContactNumber(dto.getContactNumber());
        organizer.setRole(Role.ORGANIZER);
        return organizer;
    }
}
