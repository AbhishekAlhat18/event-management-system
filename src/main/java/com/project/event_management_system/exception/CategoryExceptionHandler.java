package com.project.event_management_system.exception;

import com.project.event_management_system.dto.ErrorResponseDTO;
import com.project.event_management_system.exception.category.CategoryAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CategoryExceptionHandler.class);

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDTO> handleCategoryAlreadyExistsException(CategoryAlreadyExistException ex) {
        log.warn("Category Already Exists: {}", ex.getMessage());
        // Option 2: Log the full exception (uncomment if needed)
        // log.warn("Category Already Exists", ex);
        return buildErrorResponse("Category Already Exist.Please add different category.", HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT);
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
