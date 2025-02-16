package com.project.event_management_system.controller;

import com.project.event_management_system.dto.OrganizerRegistrationDTO;
import com.project.event_management_system.model.Organizer;
import com.project.event_management_system.service.PersonService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST}) //Specifies that only HTTP GET and POST methods are allowed for cross-origin requests.
@RestController  //Not good Practice
@RequestMapping("/api")
public class OrganizerRegistrationController {

    private final PersonService personService;

    @Autowired
    public OrganizerRegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register/organizer")
    public ResponseEntity<?> registerOrganizer(
            @RequestBody @Valid OrganizerRegistrationDTO organizerRegistrationDTO
    ) throws MessagingException {
        this.personService.registerOrganizer(organizerRegistrationDTO);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/validate-email/organizer")  //Add Role based authorization
    public String activateAccount(
            @RequestParam String token
    ) throws MessagingException {
        return this.personService.activateAccount(token);

    }

    @PostMapping("/resend-activation-email/organizer")
    public String resendActivationEmail(
            @RequestParam String email
    ) throws MessagingException {
        return this.personService.resendActivationEmail(email);

    }
}
