package com.project.event_management_system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.event_management_system.dto.ErrorResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // Handle different types of authentication failures

        ErrorResponseDTO errorResponse;

        if (exception instanceof DisabledException) {
             errorResponse = ErrorResponseDTO.builder()
                    .message("Your account is not activated. Please verify your email address to activate your account")
                    .status(HttpStatus.FORBIDDEN)
                    .statusCode(HttpStatus.FORBIDDEN.value())
                    .timestamp(java.time.LocalDateTime.now().toString())
                    .build();


        }else if (exception instanceof BadCredentialsException) {
             errorResponse = ErrorResponseDTO.builder()
                    .message("Invalid email or password.")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .timestamp(java.time.LocalDateTime.now().toString())
                    .build();
        }else {
            // Generic authentication failure
             errorResponse = ErrorResponseDTO.builder()
                    .message("Authentication failed: " + exception.getMessage())
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .timestamp(java.time.LocalDateTime.now().toString())
                    .build();

        }

        // Set appropriate status code
        response.setStatus(errorResponse.getStatusCode());

        // Set response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Write the JSON response
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
