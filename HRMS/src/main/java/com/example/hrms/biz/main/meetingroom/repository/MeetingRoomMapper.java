package com.example.hrms.biz.main.meetingroom.repository;

import com.example.hrms.biz.main.meetingroom.model.MeetingRoom;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MeetingRoomMapper {
    @Select("SELECT * FROM Meeting_Rooms WHERE room_id = #{roomId}")
    MeetingRoom getMeetingRoomById(Long roomId);

    @Insert("INSERT INTO Meeting_Rooms(room_name, location, capacity) VALUES(#{roomName}, #{location}, #{capacity})")
    void insertMeetingRoom(MeetingRoom meetingRoom);

    @Update("UPDATE Meeting_Rooms SET room_name = #{roomName}, location = #{location}, capacity = #{capacity} WHERE room_id = #{roomId}")
    void updateMeetingRoom(MeetingRoom meetingRoom);

    @Delete("DELETE FROM Meeting_Rooms WHERE room_id = #{roomId}")
    void deleteMeetingRoom(Long roomId);
}