package com.project.event_management_system.model;



import java.util.List;

import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="offer_table")
public class Offer{

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
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
    private Organizer organizer;

}
