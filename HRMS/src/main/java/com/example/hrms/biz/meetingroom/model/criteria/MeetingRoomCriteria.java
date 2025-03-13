package com.example.hrms.biz.meetingroom.model.criteria;


import com.example.hrms.common.http.criteria.Page;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MeetingRoomCriteria extends Page {
    private Long roomId;
    private String roomName;
    private String location;
    private Integer capacity;

    public MeetingRoomCriteria() {}

    public MeetingRoomCriteria(Long roomId,String roomName, String location, Integer capacity) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.location = location;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "MeetingRoomCriteria{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
