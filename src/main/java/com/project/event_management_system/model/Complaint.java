package com.project.event_management_system.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="complaint_table",uniqueConstraints= {@UniqueConstraint(columnNames="complaint_id")})
public class Complaint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="complaint_id",unique=true, nullable=false)
    private int complaintId;

    @Column(name="complaint_description")
    private String complaintDescription;

    @Column(name="complaint_approval")
    private String approval;

    @ManyToOne
    @JoinColumn(name="eventId")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User complainant; // The user who filed the complaint

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private Organizer accusedOrganizer;    // The organizer being reported


}

