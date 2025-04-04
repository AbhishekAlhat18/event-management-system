package com.project.event_management_system.exception.account;

public class EmailAndTokenMisMatchException extends IllegalArgumentException {
    public EmailAndTokenMisMatchException(String message) {
        super(message);
    }
}
