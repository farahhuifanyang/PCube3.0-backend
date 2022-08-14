package com.dssc.pcube3.entityservice.enums;

public enum Resource {
    ARTICLE("article"),
    ENTITY("entity"),
    EVENT("event"),
    PERSONALITY("personality"),
    PERSON_NODE("person-node"),
    PERSON_LINK("person-link"),
    REGION_NODE("region-node"),
    REGION_LINK("region-link"),
    LOCATION_NODE("location-node"),
    LOCATION_LINK("location-link"),
    ORGANIZATION_NODE("organization-node"),
    ORGANIZATION_LINK("organization-link");


    private String name;
    Resource(String name) {
        this.name = name;
    }
}
