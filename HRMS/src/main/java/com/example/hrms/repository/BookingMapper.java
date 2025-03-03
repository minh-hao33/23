package com.example.hrms.repository;

import com.example.hrms.model.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookingMapper {
    @Select("SELECT * FROM Bookings WHERE booking_id = #{bookingId}")
    Booking getBookingById(Long bookingId);

    @Insert("INSERT INTO Bookings(username, room_id, start_time, end_time, status) VALUES(#{username}, #{roomId}, #{startTime}, #{endTime}, #{status})")
    void insertBooking(Booking booking);

    @Update("UPDATE Bookings SET status = #{status}, start_time = #{startTime}, end_time = #{endTime} WHERE booking_id = #{bookingId}")
    void updateBooking(Booking booking);

    @Delete("DELETE FROM Bookings WHERE booking_id = #{bookingId}")
    void deleteBooking(Long bookingId);
}