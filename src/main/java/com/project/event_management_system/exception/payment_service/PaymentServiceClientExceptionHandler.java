package com.project.event_management_system.exception.payment_service;

import com.project.event_management_system.dto.ErrorResponseDTO;
import com.project.event_management_system.exception.AccountVerificationExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class PaymentServiceClientExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AccountVerificationExceptionHandler.class);
    // 1. Handle 401 Unauthorized (e.g., invalid API key)
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthorized(
            HttpClientErrorException.Unauthorized ex) {
        log.error("Unauthorized access: {}", ex.getMessage());
        return buildErrorResponse(
                "Unauthorized access to payment service: Please check your API configuration",
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.valueOf(ex.getStatusCode().value())
        );
    }

    // 2. Handle 500 Internal Server Error (e.g., downstream service failure)
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponseDTO> handleInternalServerError(
            HttpServerErrorException.InternalServerError ex) {
        log.error("Downstream service error: {}", ex.getMessage());
        return buildErrorResponse(
                "Internal server error: Please try again later",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.valueOf(ex.getStatusCode().value())
        );
    }

    // 3. Fallback for other HTTP 4xx errors (e.g., 400, 404)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpClientError(
            HttpClientErrorException ex) {
        log.warn("Client error: {}", ex.getMessage());
        return buildErrorResponse(
                ex.getStatusText(), // "Bad Request", "Not Found", etc.
                ex.getStatusCode().value(),
                HttpStatus.valueOf(ex.getStatusCode().value())
        );
    }

    // 4. Fallback for other HTTP 5xx errors (e.g., 502, 503)
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpServerError(
            HttpServerErrorException ex) {
        log.error("Server error: {}", ex.getMessage());
        return buildErrorResponse(
                "Service unavailable: Please contact support",
                ex.getStatusCode().value(),
                HttpStatus.valueOf(ex.getStatusCode().value())
        );
    }

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(String message, int errorCode, HttpStatus status) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .message(message)
                .status(status)
                .statusCode(errorCode)
                .timestamp(java.time.LocalDateTime.now().toString())
                .build();
        return ResponseEntity.status(status).body(errorResponseDTO);
    }
}
