package com.project.event_management_system.model;



import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



//Entity and table is not required:
@Entity
@Table(name ="registration")
public class Registration{

    @Id
    @GeneratedValue( strategy= GenerationType. AUTO, generator="native" )
    //@GeneratedValue( strategy= GenerationType. AUTO )
    private int registrationId;

    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 20)
    private String role;
    @Column(nullable = false, length = 15)
    private String contactNumber;
    @Column(length = 30)
    private String verifiedCode;

    private Boolean authorize;

    @OneToMany(mappedBy="registration", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> booking; //user bookings

    @OneToMany(mappedBy="registration", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Complaint> complaint;  // Complaints made by this user

    @OneToMany(mappedBy="registration", cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Event> event;

    @OneToMany(mappedBy="organizer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Offer> offer;

    @OneToMany(mappedBy="organizerId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings; //Bookings for this organizer

    @OneToMany(mappedBy="organizerId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Complaint> complaints;  // Complaints against this organizer

    @OneToMany(mappedBy="registrationRequest", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Token> tokens;

    public List<Offer> getOffer() {
        return offer;
    }

    public void setOffer(List<Offer> offer) {
        this.offer = offer;
    }


    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public void setAuthorize(Boolean authorize) {
        this.authorize = authorize;
    }

    public boolean isAuthorize()
    {
        return authorize;
    }

    public Boolean getAuthorize() {
        return authorize;
    }

    public void setVerifiedCode(String verifiedCode) {
        this.verifiedCode = verifiedCode;
    }

    public String getVerifiedCode() {
        return verifiedCode;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }


    @PrePersist
    public void prePersist() {
        if (authorize == null) {
            authorize = false;
        }
    }

    @Override
    public String toString() {
        return "Registration [registrationId=" + registrationId + ", firstName=" + firstName + ", lastName="
                + lastName + ", email=" + email + ", password=" + password + ", role=" + role + ", contactNumber="
                + contactNumber + ", booking=" + booking + ", verifiedCode=" + verifiedCode + ", authorize=" + authorize
                + "]";
    }



}
