package com.example.hrms.model;

public class MeetingRoom {
    private Long roomId;
    private String roomName;
    private String location;
    private Integer capacity;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
