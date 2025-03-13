package com.example.hrms.biz.user.model;

import com.example.hrms.enumation.RoleEnum;
import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private Long departmentId;
    private RoleEnum role;
    private boolean isSupervisor;
    private String status;

    public void setIsSupervisor(boolean supervisor) {
    }
}