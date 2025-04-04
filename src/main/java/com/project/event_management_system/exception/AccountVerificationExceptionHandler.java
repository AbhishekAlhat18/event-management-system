package com.project.event_management_system.exception;

import com.project.event_management_system.dto.ErrorResponseDTO;
import com.project.event_management_system.exception.account.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AccountVerificationExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AccountVerificationExceptionHandler.class);


    //Handle Validation Errors  //Field Level
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        log.warn("Email Already Exists: {}", ex.getMessage());
        return buildErrorResponse("Looks like you already have an account. Please log in to continue.", HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailNotFoundException(EmailNotFoundException ex) {
        log.warn("Invalid Email: {}", ex.getMessage());
        return buildErrorResponse("Invalid Credentials.", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountAlreadyVerifiedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccountAlreadyVerifiedException(AccountAlreadyVerifiedException ex) {
        log.warn("Account Already Verified : {}", ex.getMessage());
        return buildErrorResponse("Account already verified. Please log in.", HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponseDTO> handleTokenExpiredException(TokenExpiredException ex) {
        log.warn("Token Expired : {}", ex.getMessage());
        return buildErrorResponse("Code Expired. Please request a new verification code.", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleTokenExpiredException(TokenNotFoundException ex) {
        log.warn("Token Not Found : {}", ex.getMessage());
        return buildErrorResponse("Invalid Verification Code.", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAndTokenMisMatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleTokenExpiredException(IllegalArgumentException ex) {
        log.warn(" Illegal Argument : {}", ex.getMessage());
        return buildErrorResponse("Invalid Verification Code.", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmailSendingFailedException.class)
    public ResponseEntity<String> handleEmailSendingFailed(EmailSendingFailedException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

   //Any other exception
//   @ExceptionHandler(Exception.class)
//   public ResponseEntity<String> handleGenericException(Exception ex) {
//       // Log the error for internal tracking
//       log.error("Unexpected error occurred.", ex);
//       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//               .body("An unexpected error occurred.");
//   }

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
