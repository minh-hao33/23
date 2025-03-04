package com.example.hrms.biz.main.booking.model;

import com.example.hrms.enumation.BookingStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Long bookingId;
    private String username;
    private Long roomId;
    private Date startTime;
    private Date endTime;
    private BookingStatusEnum status;
}