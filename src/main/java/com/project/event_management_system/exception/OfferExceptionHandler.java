package com.project.event_management_system.exception;

import com.project.event_management_system.dto.ErrorResponseDTO;
import com.project.event_management_system.exception.offer.OfferAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OfferExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(OfferExceptionHandler.class);

    @ExceptionHandler(OfferAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleOfferAlreadyExistsException(OfferAlreadyExistsException ex) {


        log.warn("Offer Already Exists: {}", ex.getMessage());
        return buildErrorResponse("Offer Already Exist.Please add different offer.", HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT);
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
