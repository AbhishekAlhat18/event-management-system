package com.project.event_management_system.model;


import com.project.event_management_system.enums.BookingStatus;
import com.project.event_management_system.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name="booking_table")
public class Booking {

    @Id
    @GeneratedValue( strategy= GenerationType. IDENTITY )
    @Column(unique=true, nullable=false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="event_id", nullable = false)
    private Event event;

    @Column(name = "number_of_seats", nullable = false)
    @Min(1)
    private int numberOfSeats;

    @Column(name = "selected_seats")    //Needs to be a separate Entity
    private String selectedSeats;     //For events like movie where user can select seat like

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PENDING'")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PENDING'")
    private BookingStatus bookingStatus;

    @Column(name = "booked_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime bookedAt;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @CreationTimestamp
    @Column(name = "booking_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "booking_updated_at")
    private LocalDateTime updatedAt;

}
