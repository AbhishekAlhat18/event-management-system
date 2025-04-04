package com.project.event_management_system.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.project.event_management_system.enums.EventStatus;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="event_table")
@JsonIgnoreProperties(value = {"complaints","bookings", "hibernateLazyInitializer"})
public class Event {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name="country", nullable = false)
    private String country;

    @Column(name="state", nullable = false)
    private String state;

    @Column(name="city", nullable = false)
    private String city;

    @Column(name="street_address", nullable = false)
    private String street;

    @Column(name="venue_name", nullable = false)
    private String venue;

    @Column(name="zipcode", nullable = false)
    private int zipcode;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="total_seats", nullable = false)
    private int totalSeats;

    @Column(name="base_ticket_price", nullable = false)
    private double baseTicketPrice;

    @Column(name="current_ticket_price", nullable = false)
    private double currentTicketPrice;

    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)  //Why use CascadeType.PERSIST?
    private List<EventOffer> eventOffers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private Organizer organizer;

    @OneToMany(mappedBy="event")  //Use fetch = FetchType.LAZY
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy="event")
    private List<Complaint> complaints = new ArrayList<>();

    @Column(name="event_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = EventStatus.ACTIVE;
        }
    }
    @Column(name="upvotes", nullable = false)
    private int upvotes = 0;

    @Column(name="downvotes", nullable = false)
    private int downvotes = 0; // initialized to 0 for new events

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventVote> votes = new ArrayList<>();

    @Column(name="thumbnail_url")
    private String thumbnailUrl;  // Stores the S3 image URL

    @CreationTimestamp    //Automatically sets the value of the field to the current timestamp when the entity is first saved (i.e., on INSERT).
    @Column(name="event_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp      //Automatically updates the field’s value to the current timestamp every time the entity is updated (i.e., on UPDATE).
    @Column(name="event_updated_at", nullable = false)
    private LocalDateTime updatedAt;


}
