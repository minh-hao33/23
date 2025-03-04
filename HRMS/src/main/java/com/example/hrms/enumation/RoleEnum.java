package com.example.hrms.enumation;

import lombok.Getter;

@Getter
public enum RoleEnum {
    EMPLOYEE("employee"),
    SUPERVISOR("supervisor"),
    ADMIN("admin");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

}