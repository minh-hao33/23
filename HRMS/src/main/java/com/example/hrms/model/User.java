package com.example.hrms.model;

public class User {
    private String username;
    private String password;
    private Long departmentId;
    private Long roleId;
    private Boolean isSupervisor;
    private String status;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}