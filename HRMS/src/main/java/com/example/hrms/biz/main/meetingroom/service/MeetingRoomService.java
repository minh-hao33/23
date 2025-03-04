package com.example.hrms.biz.main.meetingroom.service;

import com.example.hrms.biz.main.meetingroom.model.MeetingRoom;
import com.example.hrms.biz.main.meetingroom.repository.MeetingRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingRoomService {
    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    public MeetingRoom getMeetingRoomById(Long roomId) {
        return meetingRoomMapper.getMeetingRoomById(roomId);
    }

    public void insertMeetingRoom(MeetingRoom meetingRoom) {
        meetingRoomMapper.insertMeetingRoom(meetingRoom);
    }

    public void updateMeetingRoom(MeetingRoom meetingRoom) {
        meetingRoomMapper.updateMeetingRoom(meetingRoom);
    }

    public void deleteMeetingRoom(Long roomId) {
        meetingRoomMapper.deleteMeetingRoom(roomId);
    }
}