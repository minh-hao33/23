package com.example.hrms.biz.meetingroom.service;

import com.example.hrms.biz.meetingroom.model.MeetingRoom;
import com.example.hrms.biz.meetingroom.model.criteria.MeetingRoomCriteria;
import com.example.hrms.biz.meetingroom.model.dto.MeetingRoomDTO;
import com.example.hrms.biz.meetingroom.repository.MeetingRoomMapper;
import com.example.hrms.common.http.criteria.Page;
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
    public List<MeetingRoomDTO.Resp> list(Page page, MeetingRoomCriteria criteria) {
        page.validate();
        List<MeetingRoom> meetings = mapper.select(
                page.getPageSize(),
                page.getOffset(),
                criteria.getRoomId(),
                criteria.getRoomName(),
                criteria.getLocation(),
                criteria.getCapacity()
        );
        return meetings.stream().map(MeetingRoomDTO.Resp::toResponse).toList();
    }
}
