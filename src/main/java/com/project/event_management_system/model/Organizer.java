package com.project.event_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "organizers")
public class Organizer extends Person{

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Event> events; // Events created by the organizer

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Offer> offers; // Offers made by the organizer

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> eventBookings; // Bookings related to the organizer's events

    @OneToMany(mappedBy = "accusedOrganizer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Complaint> complaintsAgainst; // Complaints against the organizer
}
