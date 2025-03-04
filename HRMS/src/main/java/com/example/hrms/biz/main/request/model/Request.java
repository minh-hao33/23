package com.example.hrms.biz.main.request.model;

import com.example.hrms.enumation.RequestStatusEnum;
import com.example.hrms.enumation.RequestTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private Long requestId;
    private String username;
    private Long departmentId;
    private RequestTypeEnum requestType;
    private String requestReason;
    private RequestStatusEnum requestStatus;
    private String approverUsername;
    private Date startTime;
    private Date endTime;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp approvedAt;
}