package com.example.hrms.service;

import com.example.hrms.model.*;
import com.example.hrms.repository.*;
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