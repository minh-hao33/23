package com.example.hrms.biz.department.model;

import com.example.hrms.enumation.DepartmentEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Department {
    private Long departmentId;
    private String departmentName;
    private Long roleId;
    private String roleName;
}