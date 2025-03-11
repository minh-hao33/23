package com.example.hrms.biz.booking.model;

import com.example.hrms.enumation.BookingStatusEnum;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    private Long bookingId;
    private String username;
    private Long roomId;
    private Date startTime;
    private Date endTime;
    private BookingStatusEnum status;
}