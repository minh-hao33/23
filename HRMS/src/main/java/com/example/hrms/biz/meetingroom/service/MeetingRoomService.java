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