package com.example.hrms.biz.meetingroom.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MeetingRoom {
    private Long roomId;
    private String roomName;
    private String location;
    private Integer capacity;

}
