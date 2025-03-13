package com.example.hrms.biz.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/departments")
public class DepartmentController {

    @RequestMapping("")
    public String openDepartmentView(Model model) {
        return "department";
    }
}