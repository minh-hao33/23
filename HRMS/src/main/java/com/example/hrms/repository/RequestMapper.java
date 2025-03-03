package com.example.hrms.repository;

import com.example.hrms.model.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RequestMapper {
    @Select("SELECT * FROM Requests WHERE request_id = #{requestId}")
    Request getRequestById(Long requestId);

    @Insert("INSERT INTO Requests(username, department_id, request_type, request_reason, request_status, approver_username, start_time, end_time, created_at, updated_at, approved_at) VALUES(#{username}, #{departmentId}, #{requestType}, #{requestReason}, #{requestStatus}, #{approverUsername}, #{startTime}, #{endTime}, #{createdAt}, #{updatedAt}, #{approvedAt})")
    void insertRequest(Request request);

    @Update("UPDATE Requests SET request_status = #{requestStatus}, approver_username = #{approverUsername}, updated_at = #{updatedAt}, approved_at = #{approvedAt} WHERE request_id = #{requestId}")
    void updateRequest(Request request);

    @Delete("DELETE FROM Requests WHERE request_id = #{requestId}")
    void deleteRequest(Long requestId);
}