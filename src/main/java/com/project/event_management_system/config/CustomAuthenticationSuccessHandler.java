package com.project.event_management_system.config;

import com.project.event_management_system.dto.ApiResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {



        // Create your ApiResponseDTO
        ApiResponseDTO<String> responseDTO = ApiResponseDTO.<String>builder()
                .message("Login successful!")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();

        // Set appropriate status code
        response.setStatus(responseDTO.getStatusCode());

        // Set response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Write the JSON response
        objectMapper.writeValue(response.getOutputStream(), responseDTO);
    }
}
