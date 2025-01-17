package com.project.event_management_system.repository;

import com.project.event_management_system.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Integer> {
    @Query(value = "select * from offer_table where organizer_id= ?1", nativeQuery=true)
    public List<Offer> getOfferById(int id);

}
