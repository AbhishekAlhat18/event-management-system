package com.project.event_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.event_management_system.enums.EmailVerificationStatus;
import com.project.event_management_system.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // Store multiple roles in the same table
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();

    @Column(nullable = false, length = 15)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PENDING'")
    private EmailVerificationStatus emailVerificationStatus;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Token> tokens;

    @CreationTimestamp
    //Automatically sets the value of the field to the current timestamp when the entity is first saved (i.e., on INSERT).
    @Column(name="account_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    //Automatically updates the field’s value to the current timestamp every time the entity is updated (i.e., on UPDATE).It also sets the timestamp on creation because the first INSERT is technically an "update".
    @Column(name="account_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        if (emailVerificationStatus == null) {
            this.emailVerificationStatus = EmailVerificationStatus.PENDING;
        }
    }
}
