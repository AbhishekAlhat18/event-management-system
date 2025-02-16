package com.project.event_management_system.service;

import com.project.event_management_system.dto.OrganizerRegistrationDTO;
import com.project.event_management_system.dto.UserRegistrationDTO;
import jakarta.mail.MessagingException;

public interface PersonService {
    void registerUser(UserRegistrationDTO userRegistrationRequest) throws MessagingException;

    void registerOrganizer(OrganizerRegistrationDTO organizerRegistrationRequest) throws MessagingException;

    public String activateAccount(String token);

    public String resendActivationEmail(String email) throws MessagingException;
}
