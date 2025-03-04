package com.example.hrms.biz.main.user.model;

import com.example.hrms.enumation.RoleEnum;
import com.example.hrms.enumation.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private Long departmentId;
    private RoleEnum role;
    private Boolean isSupervisor;
    private StatusEnum status;
}