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

}
