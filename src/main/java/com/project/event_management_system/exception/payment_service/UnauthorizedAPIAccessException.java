package com.project.event_management_system.exception.payment_service;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class UnauthorizedAPIAccessException extends HttpClientErrorException {
    public UnauthorizedAPIAccessException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
