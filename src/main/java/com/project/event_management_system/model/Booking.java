package com.project.event_management_system.model;



import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="booking_table")
public class Booking {

    @Id
    @GeneratedValue( strategy= GenerationType. AUTO, generator="native" )
    //@GeneratedValue( strategy= GenerationType. AUTO)
    @Column(name="booking_id",unique=true, nullable=false)
    private int bookingId;

    @Column(name="booking_status")
    private String status;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Registration registration;


    @ManyToOne
    @JoinColumn(name="organizer_id")
    private Registration organizerId;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Registration getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Registration organizerId) {
        this.organizerId = organizerId;
    }
}
