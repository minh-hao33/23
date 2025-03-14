package com.example.hrms.biz.department.model.criteria;

import com.example.hrms.common.http.criteria.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentCriteria extends Page {
    private Long departmentId;
    private String departmentName;
    private Long roleId;

    public DepartmentCriteria() {}

    public DepartmentCriteria(Long departmentId, String departmentName, Long roleId, String roleName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.roleId = roleId;
    }
}