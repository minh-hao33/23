package com.example.hrms.biz.meetingroom.service;

import com.example.hrms.biz.meetingroom.model.MeetingRoom;
import com.example.hrms.biz.meetingroom.model.criteria.MeetingRoomCriteria;
import com.example.hrms.biz.meetingroom.model.dto.MeetingRoomDTO;
import com.example.hrms.biz.meetingroom.repository.MeetingRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomService {
//    @Autowired
//    private MeetingRoomMapper meetingRoomMapper;
//
//    public List<MeetingRoomDTO.Resp> listMeetingRooms(MeetingRoomCriteria criteria) {
//        List<MeetingRoom> rooms = meetingRoomMapper.getMeetingRooms(criteria);
//        return rooms.stream()
//                .map(MeetingRoomDTO.Resp::toResponse)
//                .toList();
//    }
//
//    public MeetingRoom getMeetingRoomById(Long roomId) {
//        return meetingRoomMapper.getMeetingRoomById(roomId);
//    }
//
//    public void insertMeetingRoom(MeetingRoom meetingRoom) {
//        meetingRoomMapper.insertMeetingRoom(meetingRoom);
//    }
//
//    public void updateMeetingRoom(MeetingRoom meetingRoom) {
//        meetingRoomMapper.updateMeetingRoom(meetingRoom);
//    }
//
//    public void deleteMeetingRoom(Long roomId) {
//        meetingRoomMapper.deleteMeetingRoom(roomId);
//    }

    private final MeetingRoomMapper mapper;

    public MeetingRoomService(MeetingRoomMapper mapper) {
        this.mapper = mapper;
    }

    public int count(MeetingRoomCriteria criteria) {
        return mapper.count(criteria);
    }
    public List<MeetingRoomDTO.Resp> list(MeetingRoomCriteria criteria) {
        List<MeetingRoom> meetings = mapper.select(criteria);
        return meetings.stream().map(MeetingRoomDTO.Resp::toResponse).toList();
    }
}