package com.example.hrms.model;

import java.time.LocalDateTime;

public class Booking {
    private Long bookingId;
    private String username;
    private Long roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}