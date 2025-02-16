package com.project.event_management_system.repository;

import com.project.event_management_system.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String Token);

    // Update to query by Person's id or a concrete subclass (User or Organizer)
    @Query("SELECT t FROM Token t WHERE t.person.id = :personId")
    Optional<Token> findTokenByPersonId(@Param("personId") Integer personId);

}
