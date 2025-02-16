package com.project.event_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends Person {

    //CascadeType.MERGE and CascadeType.PERSIST means bookings will only be persisted and merged when the user is persisted or merged, but not deleted when the user is deleted.
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})   //CascadeType.ALL means that if the user is deleted, all the bookings made by the user will also be deleted.
    @JsonIgnore
    private List<Booking> userBookings; // Bookings made by the user

    @OneToMany(mappedBy = "complainant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Complaint> complaints; // Complaints made by the user

}
