package com.project.event_management_system.service.implementation;

import com.project.event_management_system.model.Booking;
import com.project.event_management_system.repository.BookingRepository;
import com.project.event_management_system.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImplementation implements BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImplementation(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBooking(int id) {
        this.bookingRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Booking updatebooking(int id, Booking booking) {
        Booking existingbooking = bookingRepository.findById(id).get();
        if(booking.getStatus()!=null)
            existingbooking.setStatus(booking.getStatus());

        return bookingRepository.save(existingbooking);
    }

    @Override
    public List<Booking> getBookingByUserId(int id) {
        return bookingRepository.getBookingByUserId(id);
    }

    @Override
    public List<Booking> getBookingByOrganizerId(int id) {
        return bookingRepository.getBookingByOrganizerId(id);
    }
}
