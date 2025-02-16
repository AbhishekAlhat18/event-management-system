package com.project.event_management_system.controller;

import com.project.event_management_system.dto.UserRegistrationDTO;
import com.project.event_management_system.model.User;
import com.project.event_management_system.service.PersonService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST}) //Specifies that only HTTP GET and POST methods are allowed for cross-origin requests.
@RestController  //Not good Practice
@RequestMapping("/api")
public class UserRegistrationController {

    private final PersonService personService;

    @Autowired
    public UserRegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(
            @RequestBody @Valid UserRegistrationDTO userRegistrationRequest
    ) throws MessagingException {
        this.personService.registerUser(userRegistrationRequest);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/validate-email/user")  //Add Role based authorization   //
    public String activateAccount(
            @RequestParam String token
    ) throws MessagingException {
        return this.personService.activateAccount(token);

    }

    @PostMapping("/resend-activation-email/user")
    public String resendActivationEmail(
            @RequestParam String email
    ) throws MessagingException {
        return this.personService.resendActivationEmail(email);

    }
}
