package com.project.event_management_system.repository;

import com.project.event_management_system.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    @Query("SELECT u FROM Registration u WHERE u.email = ?1")
    public Registration findByEmail(String email);

    @Query("SELECT u FROM Registration u WHERE u.verifiedCode = ?1")
    public Registration findByVerifiedCode(String code);

    @Query("FROM Registration where role =?1")
    public Registration findByRole(String role);

    @Query("FROM Registration where role = 'organizer'")
    public List<Registration> getOrganizer();

    @Query("From Registration where role = 'User'")
    public List<Registration> getUser();

}
