package com.project.event_management_system.model;




import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="category_table")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )

    @Column(name="id",unique=true, nullable=false)
    private Long id;

    @Column(name="name",unique=true,nullable=false)
    private String name;

    @OneToMany(mappedBy="category")
    @JsonIgnore     //Later makes this fetch = FetchType.LAZY
    private List<Event> events;

    @CreationTimestamp
    @Column(name = "category_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "category_updated_at")
    private LocalDateTime updatedAt;

}
