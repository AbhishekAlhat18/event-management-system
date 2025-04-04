package com.project.event_management_system.service.implementation;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.OrganizerRegistrationDTO;
import com.project.event_management_system.dto.UserRegistrationDTO;
import com.project.event_management_system.enums.EmailTemplateName;

import com.project.event_management_system.enums.EmailVerificationStatus;
import com.project.event_management_system.enums.Role;
import com.project.event_management_system.exception.account.*;
import com.project.event_management_system.mapper.PersonMapper;
import com.project.event_management_system.model.User;

import com.project.event_management_system.model.Token;
import com.project.event_management_system.repository.UserRepository;

import com.project.event_management_system.repository.TokenRepository;
import com.project.event_management_system.service.EmailService;
import com.project.event_management_system.service.UserService;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {


    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PersonMapper personMapper;


    @Value("${application.mailing.frontend.activation-url}")  //from application properties
    private String activationUrl;


    @Autowired
    public UserServiceImplementation(TokenRepository tokenRepository, EmailService emailService, UserRepository userRepository, PersonMapper personMapper) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.personMapper = personMapper;
    }


    @Override
    public ApiResponseDTO<Void> registerUser(UserRegistrationDTO userRegistrationDTO)  {
            if(userRepository.existsByEmail(userRegistrationDTO.getEmail())){
                throw new EmailAlreadyExistsException("User with email: " + userRegistrationDTO.getEmail() + " already exists");
            }

            // Convert the DTO to an entity
            User user = personMapper.toUserEntity(userRegistrationDTO);

            if (!user.getRoles().contains(Role.USER)) {
                throw new IllegalArgumentException("Role mismatch: Expected USER");
            }

            userRepository.save(user);

            sendValidationEmail(user);

        return ApiResponseDTO.<Void>builder()
                .message("Registration successful! Please check your email for verification code.")
                .status(HttpStatus.ACCEPTED)
                .statusCode(HttpStatus.ACCEPTED.value())
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public void registerOrganizer(OrganizerRegistrationDTO organizerRegistrationRequest) throws MessagingException {
        // Convert the DTO to an entity
//        Organizer organizer = personMapper.toOrganizerEntity(organizerRegistrationRequest);
//
//        if ( organizer.getRole() != Role.ORGANIZER) {
//            throw new IllegalArgumentException("Role mismatch: Expected ORGANIZER for Organizer entity");
//        }
//
//        personRepository.save(organizer);
//
//        sendValidationEmail(organizer);

    }

    private void sendValidationEmail(User registrationRequest) {
        try {
            var newToken = generateAndSaveActivationToken(registrationRequest);
            emailService.sendEmail(registrationRequest.getEmail(), registrationRequest.getFirstName(), EmailTemplateName.ACTIVATE_ACCOUNT, activationUrl, newToken, "Account Activation");
        } catch (MessagingException e) {
            System.out.println("Failed to send email");
            throw new EmailSendingFailedException("Failed to send activation email. Please try again later.", e);
        }
    }



    private String generateAndSaveActivationToken(User registrationRequest) {
        String generatedToken = generateActivationCode(6);
        var token  =  Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(registrationRequest)
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
    public ApiResponseDTO<Void> activateAccount(String email,String token) {

        boolean userExists = this.userRepository.existsByEmail(email);

        if (!userExists) {
            throw new EmailNotFoundException("User or Organizer with email: " + email + " not found.");
        }

        Token savedToken = this.tokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenNotFoundException("Token: " + token + " not found"));


        if (savedToken.getUser().getEmail().equals(email)) {
            var person1 = savedToken.getUser();

            if (person1.getEmailVerificationStatus() == EmailVerificationStatus.VERIFIED) {
                throw new AccountAlreadyVerifiedException("Account already verified for email: " + email);
            }

            if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
                throw new TokenExpiredException("Token Expired. Please request a new activation email.");
            }

            person1.setEmailVerificationStatus(EmailVerificationStatus.VERIFIED);
            this.userRepository.save(person1);

            savedToken.setVerifiedAt(LocalDateTime.now());
            this.tokenRepository.save(savedToken);

            return ApiResponseDTO.<Void>builder()
                    .message("Account activated successfully! You can now Login.")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .timestamp(LocalDateTime.now().toString())
                    .build();

        }else{
            throw  new EmailAndTokenMisMatchException("Email: " + email + " does not match the email associated with the token: " + token);
        }
    }

    @Override
    public ApiResponseDTO<Void> resendActivationEmail(String email)  {


        Optional<User> person = this.userRepository.findByEmail(email);

        if (person.isEmpty()) {
            throw new EmailNotFoundException("User or Organizer with email " + email + " not found.");
        }

        if (person.get().getEmailVerificationStatus() == EmailVerificationStatus.VERIFIED) {
            throw new AccountAlreadyVerifiedException("Account already verified for email: " + email);
        }


        // Retrieve the associated token
        Token token = this.tokenRepository.findTokenByUserId(person.get().getId())

                .orElseThrow(() -> new RuntimeException("Token Data associated with person id " + person.get().getId() + "not found"));

        String newTokenValue;

            // Generate a new token if it doesn't exist or is expired
        newTokenValue = generateActivationCode(6);

            token.setUser(person.get());
            token.setToken(newTokenValue);
            token.setCreatedAt(LocalDateTime.now());
            token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            token.setVerifiedAt(null);
            tokenRepository.save(token); // update token

           try {
               sendEmailWithToken(person.get(), newTokenValue);
           } catch (MessagingException e) {
                throw new EmailSendingFailedException("Failed to send activation email. Please try again later.", e);
           }

            return ApiResponseDTO.<Void>builder()
                    .message("A new activation code has been sent to " + email + ".")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .timestamp(LocalDateTime.now().toString())
                    .build();
    }

    private void sendEmailWithToken(User user, String tokenValue) throws MessagingException {
        // Send the email with the appropriate token
        String activationUrl = "http://your-activation-url?token=" + tokenValue;
        emailService.sendEmail(user.getEmail(), user.getFirstName(),
                EmailTemplateName.ACTIVATE_ACCOUNT, activationUrl, tokenValue,
                "Account Activation");
    }
}
