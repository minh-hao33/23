package com.example.hrms.biz.main.department.model;

import lombok.Getter;
import lombok.Setter;

public class Department {
    @Getter
    @Setter
    private Long departmentId;
    private String departmentName;
    private Long roleId;

}