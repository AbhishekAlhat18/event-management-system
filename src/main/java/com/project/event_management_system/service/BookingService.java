package com.project.event_management_system.service;

import com.project.event_management_system.model.Booking;

import java.util.List;

public interface BookingService {

    public Booking createBooking(Booking booking);

    public List<Booking> getBooking();

    public Booking getBookingById(int id);

    public void deleteBooking(int id);

    public Booking updatebooking(int id, Booking booking);

    public List<Booking> getBookingByUserId(int id);

    public List<Booking> getBookingByOrganizerId(int id);


}
