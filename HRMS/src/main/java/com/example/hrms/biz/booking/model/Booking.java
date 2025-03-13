package com.example.hrms.biz.booking.model;

import com.example.hrms.enumation.BookingStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    private Long bookingId;
    private String username;
    private Long roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatusEnum status;
}