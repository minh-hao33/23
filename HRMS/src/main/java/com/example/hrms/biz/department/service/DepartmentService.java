package com.example.hrms.biz.department.service;

import com.example.hrms.biz.department.model.Department;
import com.example.hrms.biz.department.model.criteria.DepartmentCriteria;
import com.example.hrms.biz.department.model.dto.DepartmentDTO;
import com.example.hrms.biz.department.repository.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public void insert(DepartmentDTO.Req req) {
        Department department = req.toDepartment();
        departmentMapper.insertDepartment(department);
    }

    public void updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);
    }

    public void deleteDepartment(Long departmentId) {
        departmentMapper.deleteUsersByDepartmentId(departmentId);
        departmentMapper.deleteDepartment(departmentId);
    }

    public int count(DepartmentCriteria criteria) {
        return departmentMapper.count(criteria);
    }

    public List<DepartmentDTO.Resp> list(DepartmentCriteria criteria) {
        List<Department> departments = departmentMapper.selectAll();
        return departments.stream().map(DepartmentDTO.Resp::toResponse).toList();
    }

    public List<Department> getAllDepartments() {
        return departmentMapper.selectAll();
    }
}