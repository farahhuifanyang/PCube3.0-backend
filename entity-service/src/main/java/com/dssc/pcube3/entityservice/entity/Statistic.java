package com.dssc.pcube3.entityservice.entity;

import lombok.Data;

@Data
public class Statistic {

    private String name;

    private long dataCount;

    public Statistic(String name, long dataCount) {
        this.name = name;
        this.dataCount = dataCount;
    }
}
