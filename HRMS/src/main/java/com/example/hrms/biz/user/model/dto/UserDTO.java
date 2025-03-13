package com.example.hrms.biz.user.model.dto;

import com.example.hrms.enumation.RoleEnum;
import lombok.Data;

public class UserDTO {

    @Data
    public static class Req {
        private String username;
        private String password;
        private Long departmentId;
        private RoleEnum role;
        private boolean isSupervisor;
        private String status;
    }

    @Data
    public static class Resp {
        private String username;
        private Long departmentId;
        private RoleEnum role;
        private boolean isSupervisor;
        private String status;
    }
}