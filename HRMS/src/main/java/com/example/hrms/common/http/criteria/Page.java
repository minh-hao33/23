package com.example.hrms.common.http.criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

    private int pageSize = 10;

    private int pageNo = 0;

    private int total = 0;

    public int getOffset() {
        return (pageNo * pageSize);
    }

    public void validate() {
        if (pageSize < 1) {
            pageSize = 10; // Default value
        }
        if (pageNo < 0) {
            pageNo = 0; // Default to the first page
        }
    }

}
