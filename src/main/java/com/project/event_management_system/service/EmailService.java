package com.project.event_management_system.service;

import com.project.event_management_system.enums.EmailTemplateName;
import jakarta.mail.MessagingException;

public interface EmailService {

    public void sendEmail(

            String to,
            String username,
            EmailTemplateName emailTemplateName,
            String ConfirmationUrl,
            String activationCode,
            String subject
    ) throws MessagingException;
}
