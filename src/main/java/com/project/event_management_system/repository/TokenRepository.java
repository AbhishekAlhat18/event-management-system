package com.project.event_management_system.repository;

import com.project.event_management_system.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String Token);

    @Query("SELECT t FROM Token t WHERE t.registrationRequest.registrationId = :registrationId")
    Optional<Token> findTokenByUserId(@Param("registrationId") Integer registrationId);

}
