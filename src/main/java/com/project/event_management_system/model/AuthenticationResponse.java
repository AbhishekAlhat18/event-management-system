package com.project.event_management_system.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class AuthenticationResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    private List<Booking> booking; //user bookings

    private List<Complaint> complaint;  // Complaints made by this user

    private List<Event> event;

    private List<Offer> offer;


}
