package com.project.event_management_system.repository;

import com.project.event_management_system.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {
//
//    boolean existsByNameAndOrganizerId(String name , Long organizerId);
//
//    List<Offer> findByIdInAndOrganizerId(List<Integer> ids, Long organizerId);
//
//    @Query("SELECT o FROM Offer o WHERE o.id IN :ids AND o.organizer.id = :organizerId")
//    List<Offer> findOffersByIdsAndOrganizer(@Param("ids") List<Integer> ids, @Param("organizerId") int organizerId);


}
