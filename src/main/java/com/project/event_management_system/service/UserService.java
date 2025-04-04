package com.project.event_management_system.service;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.OrganizerRegistrationDTO;
import com.project.event_management_system.dto.UserRegistrationDTO;
import jakarta.mail.MessagingException;

public interface UserService {
    ApiResponseDTO<Void> registerUser(UserRegistrationDTO userRegistrationRequest);

    void registerOrganizer(OrganizerRegistrationDTO organizerRegistrationRequest) throws MessagingException;

    ApiResponseDTO<Void>  activateAccount(String email, String token);

    ApiResponseDTO<Void>  resendActivationEmail(String email) ;
}
