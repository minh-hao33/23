//package com.example.hrms.biz.meetingroom.controller;
//
//import com.example.hrms.biz.meetingroom.model.MeetingRoom;
//import com.example.hrms.biz.meetingroom.model.criteria.MeetingRoomCriteria;
//import com.example.hrms.biz.meetingroom.model.dto.MeetingRoomDTO;
//import com.example.hrms.biz.meetingroom.service.MeetingRoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller("/meeting-room")
//public class MeetingRoomController {
////    @Autowired
////    private MeetingRoomService meetingRoomService;
////
////    @GetMapping
////    public List<MeetingRoomDTO.Resp> listMeetingRooms(MeetingRoomCriteria criteria) {
////        return meetingRoomService.listMeetingRooms(criteria);
////    }
////    @GetMapping("/{id}")
////    public MeetingRoom getMeetingRoomById(@PathVariable Long id) {
////        return meetingRoomService.getMeetingRoomById(id);
////    }
////
////    @PostMapping
////    public void insertMeetingRoom(@RequestBody MeetingRoom meetingRoom) {
////        meetingRoomService.insertMeetingRoom(meetingRoom);
////    }
////
////    @PutMapping("/{id}")
////    public void updateMeetingRoom(@PathVariable Long id, @RequestBody MeetingRoom meetingRoom) {
////        meetingRoom.setRoomId(id);
////        meetingRoomService.updateMeetingRoom(meetingRoom);
////    }
////
////    @DeleteMapping("/{id}")
////    public void deleteMeetingRoom(@PathVariable Long id) {
////        meetingRoomService.deleteMeetingRoom(id);
////    }
//    @RequestMapping("")
//    public String openSampleView(Model model) { return "meeting-room"; }
//}