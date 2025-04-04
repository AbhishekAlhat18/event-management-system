package com.project.event_management_system.exception.offer;

public class OfferAlreadyExistsException extends RuntimeException {
    public OfferAlreadyExistsException(String message) {
        super(message);
    }
}
