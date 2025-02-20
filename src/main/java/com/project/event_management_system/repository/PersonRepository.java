package com.project.event_management_system.repository;

import com.project.event_management_system.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {


    Optional<Person> findByEmail(String email);


}

