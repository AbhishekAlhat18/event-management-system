package com.project.event_management_system.repository;

import com.project.event_management_system.model.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "select * from booking_table where user_id =?1", nativeQuery=true)
    public List<Booking> getBookingByUserId(int id);


    @Query(value = "select * from booking_table where organizer_id =?1", nativeQuery=true)
    public List<Booking>getBookingByOrganizerId(int id);
}
