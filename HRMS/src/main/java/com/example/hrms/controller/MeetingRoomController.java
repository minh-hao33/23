package com.example.hrms.controller;

import com.example.hrms.model.*;
import com.example.hrms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting-rooms")
public class MeetingRoomController {
    @Autowired
    private MeetingRoomService meetingRoomService;

    @GetMapping("/{id}")
    public MeetingRoom getMeetingRoomById(@PathVariable Long id) {
        return meetingRoomService.getMeetingRoomById(id);
    }

    @PostMapping
    public void insertMeetingRoom(@RequestBody MeetingRoom meetingRoom) {
        meetingRoomService.insertMeetingRoom(meetingRoom);
    }

    @PutMapping("/{id}")
    public void updateMeetingRoom(@PathVariable Long id, @RequestBody MeetingRoom meetingRoom) {
        meetingRoom.setRoomId(id);
        meetingRoomService.updateMeetingRoom(meetingRoom);
    }

    @DeleteMapping("/{id}")
    public void deleteMeetingRoom(@PathVariable Long id) {
        meetingRoomService.deleteMeetingRoom(id);
    }
}