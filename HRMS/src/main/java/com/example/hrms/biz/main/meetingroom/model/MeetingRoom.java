package com.example.hrms.biz.main.meetingroom.model;

import lombok.Getter;
import lombok.Setter;

public class MeetingRoom {
    @Setter
    @Getter
    private Long roomId;
    private String roomName;
    private String location;
    private Integer capacity;

}
