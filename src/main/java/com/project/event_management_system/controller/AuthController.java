package com.project.event_management_system.controller;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.UserRegistrationDTO;
import com.project.event_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}) //Specifies that only HTTP GET and POST methods are allowed for cross-origin requests.
@RestController  //Not good Practice
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<Void>> registerUser(
            @RequestBody @Valid UserRegistrationDTO createUserRegistrationRequest
    ) {
        ApiResponseDTO<Void> response = this.userService.registerUser(createUserRegistrationRequest);
        return ResponseEntity.accepted().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        String response = userService.authenticate(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/verify-email")  //Add Role based authorization   //
    public ResponseEntity<ApiResponseDTO<Void>> activateAccount(
            @RequestParam String email, @RequestParam String token
    ) {
        ApiResponseDTO<Void> response = this.userService.activateAccount(email,token);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/resend-activation-email")
    public ResponseEntity<ApiResponseDTO<Void>> resendActivationEmail(
            @RequestParam String email
    )  {
        ApiResponseDTO<Void> response = this.userService.resendActivationEmail(email);
        return ResponseEntity.ok().body(response);

    }
}
