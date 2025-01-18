package com.project.event_management_system.repository;

import com.project.event_management_system.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    @Query("SELECT u FROM Registration u WHERE u.email = ?1")
    public Optional<Registration> findByEmail(String email);

    @Query("SELECT u FROM Registration u WHERE u.verificationCode = ?1")
    public Registration findByVerificationCode(String code);

    @Query("FROM Registration where role =?1")
    public Registration findByRole(String role);

    @Query("FROM Registration where role = 'organizer'")
    public List<Registration> getOrganizer();

    @Query("From Registration where role = 'User'")
    public List<Registration> getUser();

}
