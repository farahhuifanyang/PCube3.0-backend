package com.dssc.pcube3.entityservice.entity;

import lombok.Data;

@Data
public class EsEntityCondition {

    private String name;

    public static final String DOCUMENT_FILED_NAME = "name";

    public static final String DOCUMENT_FILED_ALIAS = "alias";

    public EsEntityCondition(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
