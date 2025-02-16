package com.project.event_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.event_management_system.enums.EmailVerificationStatus;
import com.project.event_management_system.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Each subclass (User, Organizer) gets its own table, linked to Person using a foreign key.
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(nullable = false, length = 15)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PENDING'")
    private EmailVerificationStatus emailVerificationStatus;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Token> tokens;


    @PrePersist
    protected void prePersist() {
        if (emailVerificationStatus == null) {
            this.emailVerificationStatus = EmailVerificationStatus.PENDING;
        }
    }
}
