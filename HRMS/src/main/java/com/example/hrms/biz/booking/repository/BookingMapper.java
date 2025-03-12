package com.example.hrms.biz.booking.repository;

import com.example.hrms.biz.booking.model.Booking;
import com.example.hrms.biz.booking.model.criteria.BookingCriteria;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BookingMapper {

    @Insert("INSERT INTO bookings (booking_id, username, room_id, start_time, end_time, status) VALUES (#{bookingId}, #{username}, #{roomId}, #{startTime}, #{endTime}, #{status})")
    void insert(Booking booking);

    Booking selectById(long id);

    int count(BookingCriteria criteria);

    List<Booking> select(BookingCriteria criteria);

    @Select("SELECT * FROM bookings WHERE room_id = #{roomId} AND ((start_time < #{endTime} AND end_time > #{startTime}))")
    List<Booking> findConflictingBookings(@Param("roomId") Long roomId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    void updateBooking(Booking booking);

    void deleteBooking(Long bookingId);
}