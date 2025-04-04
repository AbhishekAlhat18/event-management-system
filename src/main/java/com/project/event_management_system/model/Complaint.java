package com.project.event_management_system.model;



import com.project.event_management_system.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="complaint_table")
public class Complaint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",unique=true, nullable=false)
    private int id;

    @Column(name="description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PENDING'")
    private ComplaintStatus status;

    @CreationTimestamp
    //Automatically sets the value of the field to the current timestamp when the entity is first saved (i.e., on INSERT).
    @Column(name="complaint_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    //Automatically updates the field’s value to the current timestamp every time the entity is updated (i.e., on UPDATE).
    @Column(name="complaint_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User complainant; // The user who filed the complaint

//    @ManyToOne
//    @JoinColumn(name = "organizer_id", nullable = false)
//    private Organizer accusedOrganizer;    // The organizer being reported

}

