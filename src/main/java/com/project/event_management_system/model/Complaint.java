package com.project.event_management_system.model;



import jakarta.persistence.*;

@Entity
@Table(name="complaint_table",uniqueConstraints= {@UniqueConstraint(columnNames="complaint_id")})
public class Complaint {

    @Id
    @GeneratedValue(strategy= GenerationType. AUTO, generator="native" )
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
    @JoinColumn(name="registration_id")
    private Registration registration;

    @ManyToOne
    @JoinColumn(name="organizer_id")
    private Registration organizerId;



    public Registration getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Registration organizerId) {
        this.organizerId = organizerId;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }



}