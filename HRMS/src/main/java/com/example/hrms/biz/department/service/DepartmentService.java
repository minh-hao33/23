package com.example.hrms.biz.department.service;

import com.example.hrms.biz.department.model.Department;
import com.example.hrms.biz.department.repository.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public Department getDepartmentById(Long departmentId) {
        return departmentMapper.getDepartmentById(departmentId);
    }

    public void insertDepartment(Department department) {
        departmentMapper.insertDepartment(department);
    }

    public void updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);
    }

    public void deleteDepartment(Long departmentId) {
        departmentMapper.deleteDepartment(departmentId);
    }
}