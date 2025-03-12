package com.example.hrms.biz.department.controller;

import com.example.hrms.biz.department.model.Department;
import com.example.hrms.biz.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public void insertDepartment(@RequestBody Department department) {
        departmentService.insertDepartment(department);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        department.setDepartmentId(id);
        departmentService.updateDepartment(department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

}