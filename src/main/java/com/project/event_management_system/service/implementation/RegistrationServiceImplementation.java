package com.project.event_management_system.service.implementation;

import com.project.event_management_system.enums.EmailTemplateName;
import com.project.event_management_system.model.Registration;
import com.project.event_management_system.model.Token;
import com.project.event_management_system.repository.RegistrationRepository;
import com.project.event_management_system.repository.TokenRepository;
import com.project.event_management_system.service.EmailService;
import com.project.event_management_system.service.RegistrationService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class RegistrationServiceImplementation implements RegistrationService {


    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final RegistrationRepository registrationRepository;


    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;


    @Autowired
    public RegistrationServiceImplementation(TokenRepository tokenRepository, EmailService emailService, RegistrationRepository registrationRepository) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public void registerUser(Registration registrationRequest) throws MessagingException {
        this.registrationRepository.save(registrationRequest);
        sendValidationEmail(registrationRequest);
    }



    private void sendValidationEmail(Registration registrationRequest ) throws MessagingException {
        var newToken = generateAndSaveActivationToken(registrationRequest);
        emailService.sendEmail(registrationRequest.getEmail(),registrationRequest.getFirstName(), EmailTemplateName.ACTIVATE_ACCOUNT,activationUrl,newToken,"Account Activation");

    }

    private String generateAndSaveActivationToken(Registration registrationRequest) {
        String generatedToken = generateActivationCode(6);
        var token  =  Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .registrationRequest(registrationRequest)
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

    @Override
    public String activateAccount(String token) {

        //validate token from user input and token generated
        Token savedToken  = this.tokenRepository.findByToken(token)
                .orElseThrow(()->new RuntimeException("Token Invalid"));

        var user1 = savedToken.getRegistrationRequest();

        if (user1.getAuthorize()) {
            throw new RuntimeException("Account already activated. Please log in.");
        }

        //check if token is expired
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())){
            throw new RuntimeException("Token Expired. Please request a new activation email.");
        }

        // Fetch the associated user
        Registration user =this.registrationRepository.findById(savedToken.getRegistrationRequest().getRegistrationId())
                .orElseThrow(()->new RuntimeException("User not Found"));

        //Persist in database and mark authorize as true(user successfully authenticated)
        user.setAuthorize(true);
        this.registrationRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());

        //return response
        return "Account Validated Successfully";

    }

    @Override
    public String resendActivationEmail(String email) throws MessagingException {

        Optional<Registration> user = this.registrationRepository.findByEmail(email);

            if (user.isEmpty()) {
                throw new RuntimeException("User with email " + email + " not found.");
            }

            if (user.get().getAuthorize()) {
                throw new RuntimeException("Account already activated. Please log in.");
            }

            // Additional Safety net

            // Retrieve the associated token
            Token token = tokenRepository.findTokenByUserId(user.get().getRegistrationId())

                    .orElseThrow(()->new RuntimeException("Token Data associated with user id " +user.get().getRegistrationId()+ "not found"));

            String tokenValue;
            // Determine whether to reuse or regenerate the token
            if (LocalDateTime.now().isBefore(token.getExpiresAt())) {
                // Reuse the existing token
                tokenValue = token.getToken();
            } else {
                // Generate a new token if it doesn't exist or is expired
                tokenValue = generateActivationCode(6);

            }
                // Update the token object
                token.setToken(tokenValue);
                token.setCreatedAt(LocalDateTime.now());
                token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
                token.setValidatedAt(null); // Clear validation time if previously set
                tokenRepository.save(token);




            // Send the email with the appropriate token
            emailService.sendEmail(user.get().getEmail(), user.get().getFirstName(),
                    EmailTemplateName.ACTIVATE_ACCOUNT, activationUrl, tokenValue,
                    "Account Activation");

        return " A new activation email has been sent to " +email +".";
    }
}
