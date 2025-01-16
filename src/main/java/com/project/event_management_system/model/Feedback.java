package com.project.event_management_system.model;



import java.io.Serializable;

import jakarta.persistence.*;

public class Feedback {

    @Id
     @GeneratedValue( strategy= GenerationType. AUTO, generator="native" )
    //@GeneratedValue( strategy= GenerationType. AUTO)

    @Column(name="feedback_id",unique=true, nullable=false)
    private int feedbackId;

    @Column(name="rating")
    private String rating;

    @Column(name="feedback_description")
    private String feedbackDescription;

//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private Registrationdemo userFeedback;


    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }


}
