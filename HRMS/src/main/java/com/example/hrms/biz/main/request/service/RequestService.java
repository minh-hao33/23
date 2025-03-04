package com.example.hrms.biz.main.request.service;

import com.example.hrms.biz.main.request.model.Request;
import com.example.hrms.biz.main.request.repository.RequestMapper;
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