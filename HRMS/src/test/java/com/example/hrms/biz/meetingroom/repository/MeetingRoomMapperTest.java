package com.example.hrms.biz.meetingroom.repository;

import com.example.hrms.biz.meetingroom.model.MeetingRoom;
import com.example.hrms.biz.meetingroom.model.criteria.MeetingRoomCriteria;
import com.example.hrms.common.http.criteria.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MeetingRoomMapperTest {

    @Autowired
    private MeetingRoomMapper mapper;

    @Test
    public void testPagination() {
        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize(2);

        MeetingRoomCriteria criteria = new MeetingRoomCriteria();
        List<MeetingRoom> meetings = mapper.select(
                page.getPageSize(),
                page.getOffset(),
                criteria.getRoomId(),
                criteria.getRoomName(),
                criteria.getLocation(),
                criteria.getCapacity()
        );

        assertEquals(2, meetings.size(), "Expected 2 rooms for page 1.");
    }

    @Test
    public void testQueryByRoomName() {
        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize(10);

        MeetingRoomCriteria criteria = new MeetingRoomCriteria();
        criteria.setRoomName("Sky Room");

        List<MeetingRoom> meetings = mapper.select(
                page.getPageSize(),
                page.getOffset(),
                criteria.getRoomId(),
                criteria.getRoomName(),
                criteria.getLocation(),
                criteria.getCapacity()
        );

        assertEquals(1, meetings.size(), "Expected 1 room matching 'Sky Room'.");
        assertEquals("Sky Room", meetings.get(0).getRoomName());
    }

    @Test
    public void testQueryWithNullCriteria() {
        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize(10);

        MeetingRoomCriteria criteria = new MeetingRoomCriteria(); // All fields null

        List<MeetingRoom> meetings = mapper.select(
                page.getPageSize(),
                page.getOffset(),
                criteria.getRoomId(),
                criteria.getRoomName(),
                criteria.getLocation(),
                criteria.getCapacity()
        );

        assertEquals(3, meetings.size(), "Expected all rooms when criteria is null.");
    }

    @Test
    public void testNoMatchingResults() {
        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize(10);

        MeetingRoomCriteria criteria = new MeetingRoomCriteria();
        criteria.setRoomName("Nonexistent Room");

        List<MeetingRoom> meetings = mapper.select(
                page.getPageSize(),
                page.getOffset(),
                criteria.getRoomId(),
                criteria.getRoomName(),
                criteria.getLocation(),
                criteria.getCapacity()
        );

        assertTrue(meetings.isEmpty(), "Expected no matching rooms.");
    }

    @Test
    public void testInvalidPaginationValues() {
        Page page = new Page();
        page.setPageNo(0); // Invalid page number
        page.setPageSize(0); // Invalid page size

        MeetingRoomCriteria criteria = new MeetingRoomCriteria();

        page.validate(); // Ensure validation works
        List<MeetingRoom> meetings = mapper.select(
                page.getPageSize(),
                page.getOffset(),
                criteria.getRoomId(),
                criteria.getRoomName(),
                criteria.getLocation(),
                criteria.getCapacity()
        );

        assertEquals(3, meetings.size(), "Expected all rooms when invalid pagination values are corrected.");
    }
}


