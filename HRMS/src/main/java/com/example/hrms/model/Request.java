package com.example.hrms.model;

import java.time.LocalDateTime;

public class Request {
    private Long requestId;
    private String username;
    private Long departmentId;
    private String requestType;
    private String requestReason;
    private String requestStatus;
    private String approverUsername;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime approvedAt;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

}