package com.example.hrms.biz.request.service;

import com.example.hrms.biz.request.model.Request;
import com.example.hrms.biz.request.repository.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    @Autowired
    private RequestMapper requestMapper;

    public Request getRequestById(Long requestId) {
        if (requestId == null) {
            return null;
        }
        return requestMapper.getRequestById(requestId);
    }

    public void insertRequest(Request request) {
        if (request == null || request.getUsername() == null || request.getDepartmentId() == null || request.getRequestType() == null || request.getRequestReason() == null || request.getRequestStatus() == null || request.getApproverUsername() == null || request.getStartTime() == null || request.getEndTime() == null) {
            return;
        }
        requestMapper.insertRequest(request);
    }

    public void updateRequest(Request request) {
        if (request == null || request.getRequestId() == null) {
            return;
        }
        Request existingRequest = requestMapper.getRequestById(request.getRequestId());
        if (existingRequest == null) {
            return;
        }
        requestMapper.updateRequest(request);
    }

    public void deleteRequest(Long requestId) {
        if (requestId == null) {
            return;
        }
        Request existingRequest = requestMapper.getRequestById(requestId);
        if (existingRequest == null) {
            return;
        }
        requestMapper.deleteRequest(requestId);
    }
}