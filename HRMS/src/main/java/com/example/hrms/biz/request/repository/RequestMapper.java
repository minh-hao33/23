package com.example.hrms.biz.request.repository;

import com.example.hrms.biz.request.model.Request;
import com.example.hrms.enumation.RequestStatusEnum;
import com.example.hrms.enumation.RequestTypeEnum;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RequestMapper {
    @Select("SELECT * FROM Requests WHERE request_id = #{requestId}")
    @Results({
            @Result(property = "requestType", column = "request_type", javaType = RequestTypeEnum.class),
            @Result(property = "requestStatus", column = "request_status", javaType = RequestStatusEnum.class)
    })
    Request getRequestById(Long requestId);

    @Insert("INSERT INTO Requests(username, department_id, request_type, request_reason, request_status, approver_username, start_time, end_time, created_at, updated_at, approved_at) VALUES(#{username}, #{departmentId}, #{requestType}, #{requestReason}, #{requestStatus}, #{approverUsername}, #{startTime}, #{endTime}, #{createdAt}, #{updatedAt}, #{approvedAt})")
    void insertRequest(Request request);

    @Update("UPDATE Requests SET username = #{username}, department_id = #{departmentId}, request_type = #{requestType}, request_reason = #{requestReason}, request_status = #{requestStatus}, approver_username = #{approverUsername}, start_time = #{startTime}, end_time = #{endTime}, created_at = #{createdAt}, updated_at = #{updatedAt}, approved_at = #{approvedAt} WHERE request_id = #{requestId}")
    void updateRequest(Request request);

    @Delete("DELETE FROM Requests WHERE request_id = #{requestId}")
    void deleteRequest(Long requestId);
}