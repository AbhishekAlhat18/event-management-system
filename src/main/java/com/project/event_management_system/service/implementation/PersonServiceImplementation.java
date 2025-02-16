package com.project.event_management_system.service.implementation;

import com.project.event_management_system.dto.OrganizerRegistrationDTO;
import com.project.event_management_system.dto.UserRegistrationDTO;
import com.project.event_management_system.enums.EmailTemplateName;

import com.project.event_management_system.enums.EmailVerificationStatus;
import com.project.event_management_system.enums.Role;
import com.project.event_management_system.mapper.PersonMapper;
import com.project.event_management_system.model.Organizer;
import com.project.event_management_system.model.Person;

import com.project.event_management_system.model.Token;
import com.project.event_management_system.model.User;
import com.project.event_management_system.repository.PersonRepository;

import com.project.event_management_system.repository.TokenRepository;
import com.project.event_management_system.service.EmailService;
import com.project.event_management_system.service.PersonService;

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
public class PersonServiceImplementation implements PersonService {


    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;


    @Value("${application.mailing.frontend.activation-url}")  //from application properties
    private String activationUrl;


    @Autowired
    public PersonServiceImplementation(TokenRepository tokenRepository, EmailService emailService, PersonRepository personRepository, PersonMapper personMapper) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }


    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) throws MessagingException {
        // Convert the DTO to an entity
        User user = personMapper.toUserEntity(userRegistrationDTO);

        if (user.getRole() != Role.USER) {
            throw new IllegalArgumentException("Role mismatch: Expected USER for User entity");
        }

        personRepository.save(user);

        // Send email
        sendValidationEmail(user);
    }

    @Override
    public void registerOrganizer(OrganizerRegistrationDTO organizerRegistrationRequest) throws MessagingException {
        // Convert the DTO to an entity
        Organizer organizer = personMapper.toOrganizerEntity(organizerRegistrationRequest);

        if ( organizer.getRole() != Role.ORGANIZER) {
            throw new IllegalArgumentException("Role mismatch: Expected ORGANIZER for Organizer entity");
        }

        personRepository.save(organizer);

        // Send email
        sendValidationEmail(organizer);
    }


    private void sendValidationEmail(Person registrationRequest ) throws MessagingException {
        var newToken = generateAndSaveActivationToken(registrationRequest);
        emailService.sendEmail(registrationRequest.getEmail(),registrationRequest.getFirstName(), EmailTemplateName.ACTIVATE_ACCOUNT,activationUrl,newToken,"Account Activation");

    }

    private String generateAndSaveActivationToken(Person registrationRequest) {
        String generatedToken = generateActivationCode(6);
        var token  =  Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .person(registrationRequest)
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

        //validate token from person input and token generated
        Token savedToken  = this.tokenRepository.findByToken(token)
                .orElseThrow(()->new RuntimeException("Token Invalid"));

        var person1 = savedToken.getPerson();

        if (person1.getEmailVerificationStatus() == EmailVerificationStatus.VERIFIED) {
            throw new RuntimeException("Account already verified. Please log in.");
        }

        //check if token is expired
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())){
            throw new RuntimeException("Token Expired. Please request a new activation email.");
        }

        // Fetch the associated user
//        Person user =this.registrationRepository.findById(savedToken.getRegistrationRequest().getRegistrationId())
//                .orElseThrow(()->new RuntimeException("User not Found"));

        //Persist in database and mark authorize as true(user successfully authenticated)
        person1.setEmailVerificationStatus(EmailVerificationStatus.VERIFIED);
        this.personRepository.save(person1);

        savedToken.setValidatedAt(LocalDateTime.now());
        this.tokenRepository.save(savedToken);

        //return response
        return "Account Validated Successfully";

    }

    @Override
    public String resendActivationEmail(String email) throws MessagingException {

        Optional<Person> person = this.personRepository.findByEmail(email);

        if (person.isEmpty()) {
            throw new RuntimeException("User with email " + email + " not found.");
        }

        if (person.get().getEmailVerificationStatus() == EmailVerificationStatus.VERIFIED) {
            throw new RuntimeException("Account already verified. Please log in.");
        }


        // Retrieve the associated token
        Token token = this.tokenRepository.findTokenByPersonId(person.get().getId())

                .orElseThrow(() -> new RuntimeException("Token Data associated with person id " + person.get().getId() + "not found"));

        String tokenValue;

        // Determine whether to reuse or regenerate the token
        if (LocalDateTime.now().isBefore(token.getExpiresAt())) {

            // Reuse the existing token
            tokenValue = token.getToken();
        } else {

            // Generate a new token if it doesn't exist or is expired
            tokenValue = generateActivationCode(6);

            // Create a new token object instead of overwriting the old one
            Token newToken = new Token();
            newToken.setToken(tokenValue);
            newToken.setCreatedAt(LocalDateTime.now());
            newToken.setExpiresAt(LocalDateTime.now().plusMinutes(15)); // Set new expiration time
            newToken.setValidatedAt(null); // Clear validation time if previously set
            newToken.setPerson(person.get()); // Associate the new token with the person
            tokenRepository.save(newToken); // Save the new token

            return sendEmailWithToken(person.get(), tokenValue);

        }
        return sendEmailWithToken(person.get(), tokenValue);

    }

    private String sendEmailWithToken(Person person, String tokenValue) throws MessagingException {
        // Send the email with the appropriate token
        String activationUrl = "http://your-activation-url?token=" + tokenValue;
        emailService.sendEmail(person.getEmail(), person.getFirstName(),
                EmailTemplateName.ACTIVATE_ACCOUNT, activationUrl, tokenValue,
                "Account Activation");

        return "A new activation email has been sent to " + person.getEmail() + ".";
    }
}
