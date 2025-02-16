package com.project.event_management_system.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="booking_table")
public class Booking {

    @Id
    @GeneratedValue( strategy= GenerationType. IDENTITY )
    //@GeneratedValue( strategy= GenerationType. AUTO)
    @Column(name="booking_id",unique=true, nullable=false)
    private int bookingId;

    @Column(name="booking_status")
    private String status;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private Organizer organizer;


}
