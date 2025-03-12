package com.example.hrms.biz.request.controller;

import com.example.hrms.biz.request.model.Request;
import com.example.hrms.biz.request.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @GetMapping("/{id}")
    public Request getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
    }

    @PostMapping
    public void insertRequest(@RequestBody Request request) {
        System.out.println("Received request: " + request);
        requestService.insertRequest(request);
    }

    @PutMapping("/{id}")
    public void updateRequest(@PathVariable Long id, @RequestBody Request request) {
        System.out.println("Received request: " + request);
        request.setRequestId(id);
        requestService.updateRequest(request);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
    }
}