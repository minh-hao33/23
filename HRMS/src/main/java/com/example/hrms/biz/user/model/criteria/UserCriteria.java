package com.example.hrms.biz.user.model.criteria;

import com.example.hrms.common.http.criteria.Page;
import com.example.hrms.enumation.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserCriteria extends Page {
    private String username;
    private Long departmentId;
    private RoleEnum role;
    private Boolean isSupervisor;
    private String status;
    private String email;

    public UserCriteria(String username, Long departmentId, RoleEnum role, Boolean isSupervisor, String status, String email) {
        this.username = username;
        this.departmentId = departmentId;
        this.role = role;
        this.isSupervisor = isSupervisor;
        this.status = status;
        this.email = email;
    }

    public List<RoleEnum> getRoles() {
        return role != null ? List.of(role) : List.of();
    }

    public List<Long> getDepartmentIds() {
        return departmentId != null ? List.of(departmentId) : List.of();
    }
}