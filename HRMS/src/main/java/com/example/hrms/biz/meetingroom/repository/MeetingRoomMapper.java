package com.example.hrms.biz.meetingroom.repository;

import com.example.hrms.biz.meetingroom.model.MeetingRoom;
import com.example.hrms.biz.meetingroom.model.criteria.MeetingRoomCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MeetingRoomMapper {

    @Select("SELECT COUNT(*) FROM meeting_rooms")
    int count(MeetingRoomCriteria criteria);

    @Select("SELECT room_id AS roomId, " +
            "room_name AS roomName, " +
            "location AS location, " +
            "capacity AS capacity " +
            "FROM meeting_rooms " +
            "WHERE (#{roomId} IS NULL OR room_id = #{roomId}) " +
            "AND (#{roomName} IS NULL OR room_name = #{roomName}) " +
            "AND (#{location} IS NULL OR location = #{location}) " +
            "AND (#{capacity} IS NULL OR capacity >= #{capacity}) " +
            "ORDER BY room_id ASC")
//    @Select("SELECT room_id AS roomId, room_name AS roomName, location, capacity FROM meeting_rooms")
    List<MeetingRoom> select(MeetingRoomCriteria criteria);
}