package com.project.event_management_system.model;

import com.project.event_management_system.enums.OrganizerApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrganizerApplication {

    @Id
    private Long id;  // Same as User ID, which maps to OrganizerProfile id

    @OneToOne
    @MapsId
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @Column(name = "organizer_application_status",nullable = false)
    private OrganizerApplicationStatus organizerApplicationStatus;

    @Column(name = "admin_comment", length = 500)
    private String comment;

    @PrePersist
    public void prePersist() {
        if (organizerApplicationStatus == null) {
            organizerApplicationStatus = OrganizerApplicationStatus.PENDING;
        }
    }

    @CreationTimestamp
    //Automatically sets the value of the field to the current timestamp when the entity is first saved (i.e., on INSERT).
    @Column(name="event_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    //Automatically updates the field’s value to the current timestamp every time the entity is updated (i.e., on UPDATE).
    @Column(name="event_updated_at", nullable = false)
    private LocalDateTime updatedAt;

}

