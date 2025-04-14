package com.example.hrms.biz.department.service;

import com.example.hrms.biz.department.model.Department;
import com.example.hrms.biz.department.model.criteria.DepartmentCriteria;
import com.example.hrms.biz.department.model.dto.DepartmentDTO;
import com.example.hrms.biz.department.repository.DepartmentMapper;
import com.example.hrms.security.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public List<Department> findById(Long id) {
        List<Department> departments = departmentMapper.findById(id);
        if (departments.isEmpty()) {
            throw new RuntimeException("Department not found: " + id);
        }
        return departments;
    }


    public void insert(DepartmentDTO.Req req) {
        if (departmentMapper.countByName(req.getDepartmentName(), null) > 0) {
            throw new IllegalArgumentException("Department name already exists");
        }

        Department department = new Department();
        department.setDepartmentName(req.getDepartmentName());
        departmentMapper.insertDepartment(department);

        if (department.getDepartmentId() == null) {
            throw new RuntimeException("Failed to insert department");
        }
    }

    public void updateDepartment(Long id, DepartmentDTO.Req req) {
        if (findById(id).isEmpty()) {
            throw new RuntimeException("Department not found");
        }
        if (departmentMapper.countByName(req.getDepartmentName(), id) > 0) {
            throw new RuntimeException("Department name already exists");
        }
        Department department = req.toDepartment();
        department.setDepartmentId(id);
        departmentMapper.updateDepartment(department);
    }

    public void deleteDepartment(Long departmentId) {
        if (findById(departmentId).isEmpty()) {
            throw new RuntimeException("Department not found");
        }

        // Cập nhật user để set department_id của họ thành null
        departmentMapper.updateUsersToNull(departmentId); // Thêm hàm này trong mapper

        // Xóa department
        departmentMapper.deleteDepartment(departmentId);
    }



    public List<Department> listWithEmployeesAndRoles(DepartmentCriteria criteria) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = SecurityUtils.getCurrentUsername();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            // Lấy tất cả phòng ban, kể cả phòng ban chưa có user
            return departmentMapper.findAllDepartmentsWithNullableUserInfo();
        } else {
            // Lấy danh sách user cùng phòng ban với user đang login
            return departmentMapper.findByDepartmentOfUser(username);
        }
    }


}