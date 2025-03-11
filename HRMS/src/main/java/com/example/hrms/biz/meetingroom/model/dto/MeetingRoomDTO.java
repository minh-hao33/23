package com.example.hrms.biz.meetingroom.model.dto;

import com.example.hrms.biz.meetingroom.model.MeetingRoom;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

public class MeetingRoomDTO {
    public static class Req{
        @Getter
        @Setter
        private Long roomId;
        @Getter
        @Setter
        private String roomName;
        @Getter
        @Setter
        private String location;
        @Getter
        @Setter
        private Integer capacity;

        public MeetingRoom toMeetingRoom() {
            MeetingRoom meetingRoom = new MeetingRoom();
            BeanUtils.copyProperties(this, meetingRoom);
            return meetingRoom;
        }
    }

    public static class Resp{
        @Getter
        @Setter
        private Long roomId;
        @Getter
        @Setter
        private String roomName;
        @Getter
        @Setter
        private String location;
        @Getter
        @Setter
        private Integer capacity;

        public static Resp toResponse(MeetingRoom meetingRoom) {
            Resp resp = new Resp();
            BeanUtils.copyProperties(meetingRoom, resp);
            return resp;
        }
    }
}
