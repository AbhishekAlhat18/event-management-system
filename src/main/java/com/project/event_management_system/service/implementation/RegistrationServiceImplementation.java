package com.project.event_management_system.service.implementation;

import com.project.event_management_system.model.Registration;
import com.project.event_management_system.model.Token;
import com.project.event_management_system.repository.TokenRepository;
import com.project.event_management_system.service.EmailService;
import com.project.event_management_system.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class RegistrationServiceImplementation implements RegistrationService {


    private final TokenRepository tokenRepository;


    @Autowired
    public RegistrationServiceImplementation(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void registerUser(Registration registrationRequest) {

        sendValidationEmail(registrationRequest);
    }

    private void sendValidationEmail(Registration registrationRequest ) {
        var newToken = generateAndSaveActivationToken(registrationRequest);
        //send Email
    }

    private String generateAndSaveActivationToken(Registration registrationRequest) {
        String generatedToken = generateActivationCode(6);
        var token  =  Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .build();
        this.tokenRepository.save(token);
        return  generatedToken;

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder activationCodeBuilder  = new StringBuilder();
        SecureRandom secureRandom  = new SecureRandom();
        for (int i = 0; i < length; i++){
            int randomIndex = secureRandom.nextInt(characters.length());
            activationCodeBuilder.append(characters.charAt(randomIndex));
        }
        return activationCodeBuilder.toString();

    }
}
