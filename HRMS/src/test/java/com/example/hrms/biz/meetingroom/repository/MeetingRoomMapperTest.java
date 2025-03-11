package com.example.hrms.biz.meetingroom.repository;

import com.example.hrms.biz.meetingroom.model.MeetingRoom;
import com.example.hrms.biz.meetingroom.model.criteria.MeetingRoomCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MeetingRoomMapperTest {

    @Autowired
    private MeetingRoomMapper mapper;

    @Test
    public void testSelect() {
        MeetingRoomCriteria criteria = new MeetingRoomCriteria(); // Set any test criteria if needed
        List<MeetingRoom> rooms = mapper.select(criteria);
        rooms.forEach(room -> System.out.println(room));
    }

    @Test
    public void testSelectRooms() {
        MeetingRoomCriteria criteria = new MeetingRoomCriteria();
        criteria.setRoomName("Sky Room");
        List<MeetingRoom> rooms = mapper.select(criteria);
        rooms.forEach(System.out::println);
    }
    @Test
    public void testSelectRoomsNull() {
        MeetingRoomCriteria criteria = new MeetingRoomCriteria();
        criteria.setRoomId(null);
        criteria.setRoomName(null);
        criteria.setLocation(null);
        criteria.setCapacity(null);

        List<MeetingRoom> rooms = mapper.select(criteria);
        rooms.forEach(System.out::println);
    }

}

