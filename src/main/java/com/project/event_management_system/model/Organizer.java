package com.project.event_management_system.model;
import com.project.event_management_system.enums.OrganizerBankAccountDetailsStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="organizers")
public class Organizer {

    @Id
    private Long id;  // Same as User ID, which maps to OrganizerProfile id

    @OneToOne
    @MapsId
    @JoinColumn(name = "organizer_id")
    private User user;

    @Column(nullable = false, length = 100)
    private String displayName;


    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 255)
    private String addressLine1;

    @Column(length = 255)
    private String addressLine2;

    @Column(nullable = false, length = 15)
    private String zipCode;

    @Column(nullable = false, length = 12)
    private String phoneNumber;

    @Column(length = 100)
    private String websiteUrl;

    @Column(length = 500)
    private String bioDescription;

    @Column(name="event_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrganizerBankAccountDetailsStatus organizerBankAccountDetailsStatus;

    @CreationTimestamp
    //Automatically sets the value of the field to the current timestamp when the entity is first saved (i.e., on INSERT).
    @Column(name="event_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    //Automatically updates the field’s value to the current timestamp every time the entity is updated (i.e., on UPDATE).
    @Column(name="event_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;

//    @OneToMany(mappedBy = "event.organizer", cascade = CascadeType.ALL)
//    private List<Complaint> complaints;

    @OneToOne(mappedBy = "organizer")
    private OrganizerApplication organizerApplication;

    @PrePersist
    public void prePersist() {
        if (organizerBankAccountDetailsStatus == null) {
            organizerBankAccountDetailsStatus = OrganizerBankAccountDetailsStatus.PENDING;
        }
    }

}

