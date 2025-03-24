package com.example.hrms.biz.booking.controller;

import com.example.hrms.biz.booking.model.Booking;
import com.example.hrms.biz.booking.model.dto.BookingDTO;
import com.example.hrms.biz.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("")
    public String openBookingView(Model model) {
        model.addAttribute("booking", new BookingDTO.Req());
        return "bookings";
    }

    @GetMapping("/view")
    public String openViewRoom(Model model) {
        List<BookingDTO.Resp> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "viewroom";
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingDetails(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
        return ResponseEntity.ok(BookingDTO.Resp.toResponse(booking));
    }

    @GetMapping("/edit/{bookingId}")
    public String openEditBookingView(@PathVariable Long bookingId, Model model) {
        Booking booking = bookingService.getBookingById(bookingId);
        model.addAttribute("booking", BookingDTO.Req.fromBooking(booking));
        return "bookingedit";
    }


    @PutMapping("/edit/{bookingId}")
    public ResponseEntity<?> editBooking(@PathVariable Long bookingId, @RequestBody BookingDTO.Req bookingReq) {
        try {
            if (bookingReq.getUsername() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username cannot be null");
            }
            if (bookingReq.getRoomId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room ID cannot be null");
            }
            // Validate the username directly within the BookingController
            Booking existingBooking = bookingService.getBookingById(bookingId);
            if (existingBooking == null || !existingBooking.getUsername().equals(bookingReq.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username");
            }
            Booking booking = bookingReq.toBooking();
            booking.setBookingId(bookingId); // Ensure the booking ID is set
            bookingService.updateBooking(booking);
            return ResponseEntity.ok("Booking updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating booking: " + e.getMessage());
        }
    }

    @GetMapping("/delete/{bookingId}")
    public String openDeletePopup(@PathVariable Long bookingId, Model model) {
        model.addAttribute("bookingId", bookingId);
        return "popup";
    }

    @PostMapping("/delete/{bookingId}")
    public String deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return "redirect:/bookings/view";
    }

    @PostMapping("/create")
    public String createBooking(@ModelAttribute BookingDTO.Req bookingReq) {
        bookingService.insert(bookingReq);
        return "redirect:/bookings/view";
    }
}
