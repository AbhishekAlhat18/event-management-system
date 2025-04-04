package com.project.event_management_system.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "feedback_id", unique = true, nullable = false)
    private int feedbackId;

    @Column(name = "rating")
    private String rating;

    @Column(name = "feedback_description")
    private String feedbackDescription;

}
