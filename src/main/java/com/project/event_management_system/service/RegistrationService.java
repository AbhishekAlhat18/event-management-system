package com.project.event_management_system.service;

import com.project.event_management_system.model.Registration;
import jakarta.mail.MessagingException;

import java.util.Optional;

public interface RegistrationService {
    public void registerUser(Registration registration) throws MessagingException;

    public String activateAccount(String token);

    public String resendActivationEmail(String email) throws MessagingException;
}
