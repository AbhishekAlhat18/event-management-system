package com.project.event_management_system.controller;

import com.project.event_management_system.model.AuthenticationRequest;
import com.project.event_management_system.model.AuthenticationResponse;
import com.project.event_management_system.repository.LoginRepository;
import com.project.event_management_system.response.Response;
import com.project.event_management_system.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

//
//    @PostMapping("/user")
//    public ResponseEntity<Response> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest){
//        AuthenticationResponse authenticationResponse  = this.loginService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
//        return ResponseEntity.ok(Response.builder()
//                .timeStamp(now())
//                .data(Map.of("name",authenticationResponse))
//                .message(" Login Successful")
//                .status(HttpStatus.OK)
//                .statusCode(HttpStatus.OK.value())
//                .build());
//    }

    @GetMapping("/user")
    public ResponseEntity<Response> userHomePage(){
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .message(" User Home Page")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @GetMapping("/organizer")
    public ResponseEntity<Response> organizerHomePage(){
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .message("Organizer Home Page")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }
}
