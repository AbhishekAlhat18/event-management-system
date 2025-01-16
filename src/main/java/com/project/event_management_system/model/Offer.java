package com.project.event_management_system.model;



import java.util.List;

import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="offer_table")
public class Offer{



    @Id
    @GeneratedValue( strategy= GenerationType. AUTO, generator="native" )
    //@GeneratedValue( strategy= GenerationType. AUTO)
    @Column(name="offer_id", unique = true, nullable = false)
    private int offerId;

    @Column(name="offer_name")
    private String offerName;

    @Column(name="offer_description")
    private String offerDescription;

    @OneToMany(mappedBy="offer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Event> event;

    @ManyToOne
    @JoinColumn(name="organizer_id")
    private Registration organizer;

    public Registration getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Registration organizer) {
        this.organizer = organizer;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }


}
