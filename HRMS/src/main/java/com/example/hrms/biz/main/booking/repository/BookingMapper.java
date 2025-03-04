package com.example.hrms.biz.main.booking.repository;

import com.example.hrms.biz.main.booking.model.Booking;
import com.example.hrms.enumation.BookingStatusEnum;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookingMapper {
    @Select("SELECT * FROM Bookings WHERE booking_id = #{bookingId}")
    @Results({
            @Result(property = "status", column = "status", javaType = BookingStatusEnum.class)
    })
    Booking getBookingById(Long bookingId);

    @Insert("INSERT INTO Bookings(username, room_id, start_time, end_time, status) VALUES(#{username}, #{roomId}, #{startTime}, #{endTime}, #{status})")
    void insertBooking(Booking booking);

    @Update("UPDATE Bookings SET username = #{username}, room_id = #{roomId}, start_time = #{startTime}, end_time = #{endTime}, status = #{status} WHERE booking_id = #{bookingId}")
    void updateBooking(Booking booking);

    @Delete("DELETE FROM Bookings WHERE booking_id = #{bookingId}")
    void deleteBooking(Long bookingId);
}