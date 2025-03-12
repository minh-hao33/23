package com.example.hrms.biz.meetingroom.model.dto;

import com.example.hrms.biz.meetingroom.model.MeetingRoom;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

public class MeetingRoomDTO {
    @Getter
    @Setter
    public static class Req{
        private Long roomId;
        private String roomName;
        private String location;
        private Integer capacity;

        public MeetingRoom toMeetingRoom() {
            MeetingRoom meetingRoom = new MeetingRoom();
            BeanUtils.copyProperties(this, meetingRoom);
            return meetingRoom;
        }
    }
    @Getter
    @Setter
    public static class Resp{
        private Long roomId;
        private String roomName;
        private String location;
        private Integer capacity;
        public static Resp toResponse(MeetingRoom meetingRoom) {
            Resp resp = new Resp();
            BeanUtils.copyProperties(meetingRoom, resp);
            return resp;
        }
    }
}
