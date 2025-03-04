package com.example.hrms.enumation;

import lombok.Getter;

@Getter
public enum BookingStatusEnum {
    REQUESTED("Requested"),
    CONFIRMED("Confirmed"),
    CANCELLED("Cancelled");

    private final String value;

    BookingStatusEnum(String value) {
        this.value = value;
    }

}
