package com.project.event_management_system.model;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="event_info",uniqueConstraints = {@UniqueConstraint(columnNames = "event_id")})
public class Event {

    @Id
    //@GeneratedValue( strategy= GenerationType. AUTO, generator="native" )
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    @Column(name="event_id", unique = true, nullable = false)
    private int eventId;

    @Column(name="event_name")
    private String eventName;

    @Column(name="event_date")
    private Date eventDate;

    @Column(name="event_location")
    private String eventLocation;

    @Column(name="event_details")
    private String eventDetails;

    @Column(name="event_price")
    private int eventPrice;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="offer_id")
    private Offer offer;

    // Updated to reference Organizer
    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    @JsonIgnoreProperties(value = {"events", "hibernateLazyInitializer"})
    private Organizer organizer;

    @OneToMany(mappedBy="event", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> booking = new ArrayList<Booking>();;

    @OneToMany(mappedBy="event", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Complaint> complaint = new ArrayList<Complaint>();

}
