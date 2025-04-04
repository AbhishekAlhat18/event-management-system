package com.project.event_management_system.model;



import java.time.LocalDateTime;

import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="offer_table") //uniqueConstraints = @UniqueConstraint(columnNames = {"name", "organizer_id"})
public class Offer{

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    @Column(name="id", unique = true, nullable = false)
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="organizer_id",nullable = false)
    @JsonIgnore
    private Organizer organizer;

    @CreationTimestamp
    @Column(name = "offer_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "offer_updated_at")
    private LocalDateTime updatedAt;

}
