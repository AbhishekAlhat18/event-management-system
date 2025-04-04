package com.project.event_management_system.exception.account;

public class EmailSendingFailedException extends RuntimeException {
    public EmailSendingFailedException(String message) {
        super(message);
    }

    // Add this constructor
    public EmailSendingFailedException(String message, Throwable cause) {
        super(message, cause); // <- Passes the cause to the RuntimeException
    }
}
