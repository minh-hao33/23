package com.example.hrms.service;

import com.example.hrms.model.*;
import com.example.hrms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingMapper bookingMapper;

    public Booking getBookingById(Long bookingId) {
        return bookingMapper.getBookingById(bookingId);
    }

    public void insertBooking(Booking booking) {
        bookingMapper.insertBooking(booking);
    }

    public void updateBooking(Booking booking) {
        bookingMapper.updateBooking(booking);
    }

    public void deleteBooking(Long bookingId) {
        bookingMapper.deleteBooking(bookingId);
    }
}