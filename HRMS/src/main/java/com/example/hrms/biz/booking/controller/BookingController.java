package com.example.hrms.biz.booking.controller;

import com.example.hrms.biz.booking.model.Booking;
import com.example.hrms.biz.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping
    public void insertBooking(@RequestBody Booking booking) {
        bookingService.insertBooking(booking);
    }

    @PutMapping("/{id}")
    public void updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        booking.setBookingId(id);
        bookingService.updateBooking(booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}