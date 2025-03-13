package com.example.hrms.biz.department.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Department {
    private Long departmentId;
    private String departmentName;
    private Long roleId;
    private String roleName;
}