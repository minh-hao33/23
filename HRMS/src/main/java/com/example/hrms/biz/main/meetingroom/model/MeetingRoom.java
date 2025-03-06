package com.example.hrms.biz.main.meetingroom.model;

import lombok.Data;

@Data
public class MeetingRoom {
    private Long roomId;
    private String roomName;
    private String location;
    private Integer capacity;

}
