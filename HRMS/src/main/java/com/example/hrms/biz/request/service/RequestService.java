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
        return requestMapper.getRequestById(requestId);
    }

    public void insertRequest(Request request) {
        requestMapper.insertRequest(request);
    }

    public void updateRequest(Request request) {
        requestMapper.updateRequest(request);
    }

    public void deleteRequest(Long requestId) {
        requestMapper.deleteRequest(requestId);
    }
}