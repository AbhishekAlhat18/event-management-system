package com.project.event_management_system.controller;


import com.project.event_management_system.model.Registration;
import com.project.event_management_system.service.RegistrationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //Post Mapping URL changed from registerUser
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid Registration registrationRequest
    ) throws MessagingException {
        this.registrationService.registerUser(registrationRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/activate-account")
    public String activateAccount(
            @RequestParam String token
    ) throws MessagingException {
        return this.registrationService.activateAccount(token);

    }

    @GetMapping("/resend-activation-email")
    public String resendActivationEmail(
            @RequestParam String email
    ) throws MessagingException {
        return this.registrationService.resendActivationEmail(email);

    }

}
