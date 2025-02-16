package com.project.event_management_system.model;



import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
@NoArgsConstructor
@Setter
@Getter
@ToString
*/

@Entity
@Table(name="category_info",uniqueConstraints= {@UniqueConstraint(columnNames="category_id")})
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    //@GeneratedValue(strategy= GenerationType. AUTO)

    @Column(name="category_id",unique=true, nullable=false)
    private int categoryId;

    @Column(name="category_name",unique=true)
    private String categoryName;


    @OneToMany(mappedBy="category", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy="categoryName")
    @JsonIgnore
    private List<Event> event;


    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



}
